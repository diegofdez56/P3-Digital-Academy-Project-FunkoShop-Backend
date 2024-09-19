package org.factoriaf5.digital_academy.funko_shop.order;

import org.factoriaf5.digital_academy.funko_shop.order.order_exceptions.OrderNotFoundException;
import org.factoriaf5.digital_academy.funko_shop.order_item.OrderItemRepository;
import org.factoriaf5.digital_academy.funko_shop.product.ProductRepository;
import org.factoriaf5.digital_academy.funko_shop.tracking.TrackingRepository;
import org.factoriaf5.digital_academy.funko_shop.user.User;
import org.factoriaf5.digital_academy.funko_shop.user.UserDTO;
import org.factoriaf5.digital_academy.funko_shop.user.UserRepository;
import org.factoriaf5.digital_academy.funko_shop.user.user_exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private TrackingRepository trackingRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addOrder_ShouldCreateOrderSuccessfully() {

        User user = new User();
        user.setId(1L);
        
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser(new UserDTO(user.getId(), null, null, null, null, null, null, null, null));
        orderDTO.setStatus("PENDING");
        orderDTO.setTotalPrice(100.0f);
        orderDTO.setTotalItems(2);
        orderDTO.setPaid(true);
        
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId(1L); 
            return order;
        });

        OrderDTO result = orderService.addOrder(orderDTO);

        assertNotNull(result);
        assertEquals("PENDING", result.getStatus());
        assertEquals(100.0f, result.getTotalPrice());
        assertEquals(2, result.getTotalItems());
        assertTrue(result.isPaid());
        verify(orderRepository, times(2)).save(any(Order.class));
    }

    @Test
    void updateOrder_ShouldUpdateOrderSuccessfully() {
       
        Long orderId = 1L;
        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setStatus("PENDING");
        
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus("SHIPPED");
        orderDTO.setTotalPrice(150.0f);
        orderDTO.setTotalItems(3);
        orderDTO.setPaid(true);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

      
        OrderDTO result = orderService.updateOrder(orderId, orderDTO);

        assertNotNull(result);
        assertEquals("SHIPPED", result.getStatus());
        assertEquals(150.0f, result.getTotalPrice());
        assertEquals(3, result.getTotalItems());
        assertTrue(result.isPaid());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void getAllOrders_ShouldReturnEmptyList() {
       
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());

        var result = orderService.getAllOrders();

        assertTrue(result.isEmpty());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getOrderById_ShouldThrowExceptionWhenNotFound() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void deleteOrder_ShouldDeleteOrderSuccessfully() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void getOrdersByUser_ShouldThrowExceptionWhenUserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> orderService.getOrdersByUser(userId));
        verify(userRepository, times(1)).findById(userId);
    }
}
