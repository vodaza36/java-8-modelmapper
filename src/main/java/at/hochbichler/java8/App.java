package at.hochbichler.java8;

import at.hochbichler.java8.domain.Address;
import at.hochbichler.java8.domain.Customer;
import at.hochbichler.java8.domain.Name;
import at.hochbichler.java8.domain.Order;
import at.hochbichler.java8.dto.OrderDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import javax.print.attribute.standard.Destination;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Order order = createOrder();
        OrderDTO orderDTO = convertToOrderDTO(order);
        System.out.println( "Result Entity->DTO: " + orderDTO);

        Order newOrder = convertToOrder(orderDTO);
        System.out.println( "Result DTO->Entity: " + newOrder);

        // Validation
        doTypeValidation();
    }

    private static Order convertToOrder(OrderDTO orderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Order order = modelMapper.map(orderDTO, Order.class);
        return order;
    }

    private static Order createOrder() {
        Customer customer = new Customer(new Name("Thomas", "Hochbichler"));
        Address billingAddress = new Address("Replodeck", "Weyer");
        return new Order(customer, billingAddress);
    }

    private static OrderDTO convertToOrderDTO(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(order, OrderDTO.class);
    }

    private static void doTypeValidation() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<OrderDTO, Order> typeMap = mapper.createTypeMap(OrderDTO.class, Order.class);
        typeMap.validate();
    }
}
