import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo
{
    private List<Product> products = new ArrayList<>();

    public ProductRepo()
    {
        //simulate database
        populateMockData();
    }

    public List<Product> getProducts()
    {
        return products;
    }


    public Optional<Product> getProductById(String id) {

        for (Product product : products) {
            if (product.id().equals(id)) return Optional.ofNullable(product);
        }
        return null;
    }

    public Product addProduct(Product newProduct) {
        products.add(newProduct);
        return newProduct;
    }

    public Product removeProduct(String id) {
        for (Product product : products) {
           if (product.id().equals(id)) {
               products.remove(product);
               return product;
           }
        } return null;
    }

    private void populateMockData()
    {
        products.add(new Product("1", "Apfel"));
        products.add(new Product("2", "Birne"));
        products.add(new Product("3", "Banane"));
        products.add(new Product("4", "Kiwi"));
        products.add(new Product("5", "Orange"));
        products.add(new Product("6", "Mango"));
        products.add(new Product("7", "Ananas"));
        products.add(new Product("8", "Pfirsich"));
        products.add(new Product("9", "Pflaume"));
        products.add(new Product("10", "Kirsche"));
    }
}
