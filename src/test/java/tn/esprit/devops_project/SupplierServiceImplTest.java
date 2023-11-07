package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;


//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


 /*
@SpringBootTest
public class SupplierServiceImplTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierServiceImpl supplierService;
    @Autowired
    private SupplierController supplierController;

    @BeforeEach

    @Test
    void retrieveAllSuppliers() {
        List<Supplier> suppliers = supplierController.getSuppliers();
        assertThat(suppliers).isNotNull();
        assertThat(suppliers).isNotInstanceOf(Supplier.class);

    }
    @Test
    void addSupplier() {
        // Create a new supplier
        Supplier supplier = new Supplier();
        supplier.setCode("Code");
        supplier.setLabel("Label");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);
        // Call the method to be tested
        Supplier addedSupplier = supplierService.addSupplier(supplier);

        // Assert the result
        assertNotNull(addedSupplier.getIdSupplier());
        assertEquals("Code", addedSupplier.getCode());
        assertEquals("Label", addedSupplier.getLabel());

    }


    @Test
    void updateSupplier() {
        // Create a new supplier
        Supplier supplier = new Supplier();
        supplier.setCode("Code");
        supplier.setLabel("Label");
        supplier.setSupplierCategory(SupplierCategory.CONVENTIONNE);

        // Save the supplier
        supplier = supplierRepository.save(supplier);

        // Modify the supplier
        supplier.setCode("Updated Code");
        supplier.setLabel("Updated Label");
        supplier.setSupplierCategory(SupplierCategory.CONVENTIONNE);
        // Call the method to be tested
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);

        // Assert the result
        assertEquals("Updated Code", updatedSupplier.getCode());
        assertEquals("Updated Label", updatedSupplier.getLabel());

    }





    @Test
    void deleteSupplier() {


        Long supplierId = 12L;
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(supplierId);

        supplier = supplierRepository.findById(supplierId).orElse(null);

        supplierService.deleteSupplier(supplierId);

        assertThat(supplier).isNotNull();
    }

}




*/

@SpringBootTest
class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllSuppliers() {
        // Arrange
        List<Supplier> expectedSuppliers = new ArrayList<>();
        expectedSuppliers.add(new Supplier());
        expectedSuppliers.add(new Supplier());


        when(supplierRepository.findAll()).thenReturn(expectedSuppliers);

        // Act
        List<Supplier> actualSuppliers = supplierService.retrieveAllSuppliers();

        // Assert
        assertEquals(expectedSuppliers, actualSuppliers);
        verify(supplierRepository, times(1)).findAll();


    }

    @Test
    void testAddSupplier() {
        Supplier expectedSupplier = new Supplier();
        expectedSupplier.setIdSupplier(1L);

        when(supplierRepository.save(any(Supplier.class))).thenReturn(expectedSupplier);

        Supplier actualSupplier = supplierService.addSupplier(new Supplier());

        assertEquals(expectedSupplier, actualSupplier);
        verify(supplierRepository, times(1)).save(any(Supplier.class));
    }


    @Test
     void testDeleteSupplier() {
        Long supplierId = 1L;

        supplierService.deleteSupplier(supplierId);

        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier expectedSupplier = new Supplier();
        expectedSupplier.setIdSupplier(supplierId);

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(expectedSupplier));

        Supplier actualSupplier = supplierService.retrieveSupplier(supplierId);

        assertEquals(expectedSupplier, actualSupplier);
        verify(supplierRepository, times(1)).findById(supplierId);
    }
}
