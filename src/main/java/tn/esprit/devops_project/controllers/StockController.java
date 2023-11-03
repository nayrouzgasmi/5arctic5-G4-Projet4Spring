package tn.esprit.devops_project.controllers;







import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.Iservices.IStockService;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class StockController {

    IStockService stockService;

    @PostMapping("/stock")
    Stock addStock(@RequestBody StockDTO stockDTO){
        Stock stock = convertDTOtoStock( stockDTO); // Méthode de conversion
        return stockService.addStock(stock);
    }

    public Stock convertDTOtoStock(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setIdStock(stockDTO.getIdStock());
        stock.setTitle(stockDTO.getTitle());
        stock.setProducts(stockDTO.getProducts());

        // Vous devrez peut-être gérer la conversion pour d'autres champs ici si nécessaire.

        return stock;
    }


    @GetMapping("/stock/{id}")
    Stock retrieveStock(@PathVariable Long id){
        return stockService.retrieveStock(id);
    }

    @GetMapping("/stock")
    List<Stock> retrieveAllStock(){
        return stockService.retrieveAllStock();
    }


}
