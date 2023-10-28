package tn.esprit.devops_project.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.repositories.StockRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import tn.esprit.devops_project.entities.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockServiceImplTest {
    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Before
    public void setup() {
        // Initialisez les mocks et injectez-les dans la classe de service
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddStock() {
        // Créez un objet Stock pour le test
        Stock stock = new Stock();
        stock.setIdStock(1L);
        when(stockRepository.save(stock)).thenReturn(stock);
        Stock result = stockService.addStock(stock);
        verify(stockRepository, times(1)).save(stock);
        assertEquals(stock, result);
    }
    @Test
    public void testRetrieveStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        Stock result = stockService.retrieveStock(stockId);
        verify(stockRepository, times(1)).findById(stockId);
        assertEquals(stock, result);
    }
    @Test
    public void testRetrieveAllStock() {
        // Créez une liste factice d'objets Stock pour le test
        List<Stock> stockList = new ArrayList<>();
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        stockList.add(stock1);
        stockList.add(stock2);
        when(stockRepository.findAll()).thenReturn(stockList);
        List<Stock> result = stockService.retrieveAllStock();
        verify(stockRepository, times(1)).findAll();
        assertEquals(stockList, result);
    }

   /* @Test

    public void testAddStock() {
        // Créez un objet Stock pour le test
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("Example Stock");

        // Appelez la méthode que vous souhaitez tester
        Stock addedStock = stockService.addStock(stock);

        // Assurez-vous que l'objet Stock ajouté n'est pas null
        assertNotNull(addedStock);

        // Vous pouvez également vérifier d'autres propriétés de l'objet Stock ajouté.
        assertEquals(stock.getIdStock(), addedStock.getIdStock());
        assertEquals(stock.getTitle(), addedStock.getTitle());
    }

    @Test
    public void testRetrieveStock() {
        Stock stock = new Stock();
        stock.setIdStock(2L);
        stock.setTitle("ou1");
        Stock addedStock = stockService.addStock(stock);

        Stock retrievedStock = stockService.retrieveStock(addedStock.getIdStock());

        assertNotNull(retrievedStock);
        assertEquals(addedStock.getIdStock(), retrievedStock.getIdStock());
    }

    @Test
    public void testRetrieveAllStock() {
        List<Stock> stockList = stockService.retrieveAllStock();

        assertNotNull(stockList);
        assertFalse(stockList.isEmpty());
    }*/


}