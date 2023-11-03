package tn.esprit.devops_project.controllers;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.devops_project.entities.Product;

import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
public class StockDTO {
     long idStock;
     String title;
     @OneToMany(mappedBy = "stock")
     Set<Product> products;

}
