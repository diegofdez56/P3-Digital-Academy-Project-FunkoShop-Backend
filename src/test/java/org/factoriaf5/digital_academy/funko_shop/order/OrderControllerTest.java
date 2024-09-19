package org.factoriaf5.digital_academy.funko_shop.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrders_ShouldReturnListOfOrders() {
       
        OrderDTO order1 = new OrderDTO();
        OrderDTO order2 = new OrderDTO();
        List<OrderDTO> orders = Arrays.asList(order1, order2);
        when(orderService.getAllOrders()).thenReturn(orders);

        ResponseEntity<List<OrderDTO>> response = orderController.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void getOrderById_ShouldReturnOrder() {
      
        Long orderId = 1L;
        Order order = new Order();
        when(orderService.getOrderById(orderId)).thenReturn(order);

        ResponseEntity<Order> response = orderController.getOrderById(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).getOrderById(orderId);
    }

    @Test
    void addOrder_ShouldCreateAndReturnOrder() {
      
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.addOrder(orderDTO)).thenReturn(orderDTO);
        
        ResponseEntity<OrderDTO> response = orderController.addOrder(orderDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderDTO, response.getBody());
        verify(orderService, times(1)).addOrder(orderDTO);
    }

    @Test
    void updateOrder_ShouldUpdateAndReturnOrder() {
        
        Long orderId = 1L;
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.updateOrder(orderId, orderDTO)).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> response = orderController.updateOrder(orderId, orderDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderDTO, response.getBody());
        verify(orderService, times(1)).updateOrder(orderId, orderDTO);
    }

    @Test
    void deleteOrder_ShouldReturnNoContent() {

        Long orderId = 1L;
        doNothing().when(orderService).deleteOrder(orderId);

        ResponseEntity<Void> response = orderController.deleteOrder(orderId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderService, times(1)).deleteOrder(orderId);
    }

    @Test
    void getOrdersByUser_ShouldReturnListOfOrders() {
        Long userId = 1L;
        OrderDTO order1 = new OrderDTO();
        OrderDTO order2 = new OrderDTO();
        List<OrderDTO> orders = Arrays.asList(order1, order2);
        when(orderService.getOrdersByUser(userId)).thenReturn(orders);

        ResponseEntity<List<OrderDTO>> response = orderController.getOrdersByUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(orderService, times(1)).getOrdersByUser(userId);
    }
}
