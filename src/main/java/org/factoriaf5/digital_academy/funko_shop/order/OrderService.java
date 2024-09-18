package org.factoriaf5.digital_academy.funko_shop.order;

import org.factoriaf5.digital_academy.funko_shop.order.order_exceptions.OrderNotFoundException;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItem;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItemDTO;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItemRepository;
import org.factoriaf5.digital_academy.funko_shop.product.Product;
import org.factoriaf5.digital_academy.funko_shop.product.ProductDTO;
import org.factoriaf5.digital_academy.funko_shop.product.ProductRepository;
import org.factoriaf5.digital_academy.funko_shop.tracking.Tracking;
import org.factoriaf5.digital_academy.funko_shop.tracking.TrackingDTO;
import org.factoriaf5.digital_academy.funko_shop.tracking.TrackingRepository;
import org.factoriaf5.digital_academy.funko_shop.user.User;
import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;
import org.factoriaf5.digital_academy.funko_shop.user.UserRepository;
import org.factoriaf5.digital_academy.funko_shop.user.user_exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TrackingRepository trackingRepository;

    // Crear una nueva orden
    public OrderDTO addOrder(OrderDTO orderDTO) {
        // Obtener el usuario
        User user = userRepository.findById(orderDTO.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + orderDTO.getUser().getId()));

        // Crear y guardar la orden
        Order order = new Order();
        order.setStatus(orderDTO.getStatus());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setTotalItems(orderDTO.getTotalItems());
        order.setPaid(orderDTO.isPaid());
        order.setUser(user);

        // Guardar la orden inicial en la base de datos
        Order savedOrder = orderRepository.save(order);

        // Verificar si orderItems no es null
        if (orderDTO.getOrderItems() != null) {
            // Mapear y guardar los ítems de la orden
            List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                    .map(orderItemDTO -> mapToOrderItem(orderItemDTO, savedOrder)) // Asegúrate de que mapToOrderItem
                                                                                   // devuelve OrderItem
                    .collect(Collectors.toList());
            orderItemRepository.saveAll(orderItems);
            savedOrder.setOrderItems(orderItems);
        }

        // Guardar el tracking si existe
        if (orderDTO.getTracking() != null) {
            Tracking tracking = new Tracking();
            tracking.setOrder(savedOrder);
            tracking.setTrackingNumber(orderDTO.getTracking().getTrackingNumber());
            trackingRepository.save(tracking);
            savedOrder.setTracking(tracking);
        }

        // Guardar la orden actualizada con los ítems y el tracking
        return mapToDTO(orderRepository.save(savedOrder));
    }

    // Actualizar una orden usando DTOs
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        // Actualizar campos de la orden
        order.setStatus(orderDTO.getStatus());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setTotalItems(orderDTO.getTotalItems());
        order.setPaid(orderDTO.isPaid());

        // Actualizar los ítems de la orden
        if (orderDTO.getOrderItems() != null) {
            List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                    .map(orderItemDTO -> mapToOrderItem(orderItemDTO, order))
                    .collect(Collectors.toList());
            orderItemRepository.saveAll(orderItems);
            order.setOrderItems(orderItems);
        }

        // Actualizar el tracking si existe
        if (orderDTO.getTracking() != null) {
            Tracking tracking = order.getTracking() != null ? order.getTracking() : new Tracking();
            tracking.setOrder(order);
            tracking.setTrackingNumber(orderDTO.getTracking().getTrackingNumber());
            trackingRepository.save(tracking);
            order.setTracking(tracking);
        }

        return mapToDTO(orderRepository.save(order));
    }

    // Obtener todas las órdenes
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Order getOrderById(Long orderId) {
        // Buscar la orden
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }

    public void deleteOrder(Long orderId) {
        // Buscar la orden
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        // Eliminar los ítems de la orden
        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            orderItemRepository.deleteAll(order.getOrderItems());
        }

        // Eliminar el tracking si existe
        if (order.getTracking() != null) {
            trackingRepository.delete(order.getTracking());
        }

        // Eliminar la orden
        orderRepository.delete(order);
    }

    public List<OrderDTO> getOrdersByUser(Integer userId) {
        // Obtener el usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        
        // Obtener las órdenes del usuario
        List<Order> orders = orderRepository.findByUser(user);

        // Mapear las órdenes a OrderDTO
        return orders.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Mapeo de OrderItemDTO a OrderItem
    private OrderItem mapToOrderItem(OrderItemDTO dto, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(dto.getId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setOrder(order);

        if (dto.getProduct() != null) {
            Product product = productRepository.findById(dto.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            orderItem.setProduct(product);
        }

        return orderItem;
    }

    // Mapeo de Order a OrderDTO
    private OrderDTO mapToDTO(Order order) {
        List<OrderItemDTO> orderItemsDTO = order.getOrderItems().stream()
                .map(this::mapToOrderItemDTO)
                .collect(Collectors.toList());

        TrackingDTO trackingDTO = order.getTracking() != null ? new TrackingDTO(
                order.getTracking().getId(),
                order.getTracking().getTrackingNumber(),
                null) : null;

        return new OrderDTO(
                order.getId(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getTotalItems(),
                order.isPaid(),
                new UserDTO(order.getUser().getId(),
                        order.getUser().getEmail(),
                        order.getUser().getPassword(),
                        null,
                        null, null, null, null, null),
                orderItemsDTO,
                trackingDTO);
    }

    // Mapeo de OrderItem a OrderItemDTO
    private OrderItemDTO mapToOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getQuantity(),
                null, // Evitar ciclo infinito
                new ProductDTO(orderItem.getProduct().getId(), orderItem.getProduct().getName(),
                        orderItem.getProduct().getImageHash(), orderItem.getProduct().getDescription(),
                        orderItem.getProduct().getPrice(), orderItem.getProduct().getStock(),
                        orderItem.getProduct().isAvailable(), null, null),
                null // Evitar ciclo infinito
        );
    }
}
