

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @InjectMocks
    private StockServiceImpl stockService;


    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @Before
    public void setup() {
        // Initialisez les mocks et injectez-les dans la classe de service
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() {
       
        Stock stock = new Stock();
        stock.setIdStock(1L);
        when(stockRepository.save(stock)).thenReturn(stock);
        Product product = new Product();
        product.setTitle("Test Product");

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.addProduct(product, 1L);

        assertNotNull(savedProduct);
        assertEquals(stock, savedProduct.getStock());
        verify(productRepository).save(product);
    }

    @Test
    public void testRetrieveProduct() {
        Product product = new Product();
        product.setIdProduct(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product retrievedProduct = productService.retrieveProduct(1L);

        assertNotNull(retrievedProduct);
        assertEquals(product, retrievedProduct);
    }

    @Test
    public void testRetrieveAllProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveAllProduct();

        assertNotNull(retrievedProducts);
        assertEquals(2, retrievedProducts.size());
    }

    @Test
    public void testRetrieveProductByCategory() {
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productRepository.findByCategory(category)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retrieveProductByCategory(category);

        assertNotNull(retrievedProducts);
        assertEquals(2, retrievedProducts.size());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    public void testRetrieveProductStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productRepository.findByStockIdStock(1L)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveProductStock(1L);

        assertNotNull(retrievedProducts);
        assertEquals(2, retrievedProducts.size());
    }
}
