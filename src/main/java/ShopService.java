import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService
{
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    public ShopService(OrderRepo orderRepo, ProductRepo productRepo)
    {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public OrderRepo getOrderRepo()
    {
        return orderRepo;
    }

    /*
        NOTE: may return an empty order!
     */
    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {

            Optional<Product> productToOrder = productRepo.getProductById(productId);

            if (productToOrder.isPresent()) {
                products.add(productToOrder.get());
            } else {
                return null;
            }
        }
        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderState.PROCESSING);
        return orderRepo.addOrder(newOrder);
    }


    public List<Product> getProducts() {
        return productRepo.getProducts();
    }

    public Product getProdcutById(String id)
    {
        if (productRepo.getProductById(id).isPresent()) {
            return productRepo.getProductById(id).get();
    }
        return null;
    }



    //Schreibt in dem ShopService eine Methode, die alle Bestellungen mit einem bestimmten Bestellstatus (Parameter)
    // in einer Liste zurückgibt. Nutzt dafür Streams
    public List<Order> getOrderByOderState(OrderState state)
    {
        List<Order>  result  = orderRepo.getOrders().stream().filter(
                order -> order.orderState() == state).
                collect(Collectors.toList());
        return result;
    }
}
