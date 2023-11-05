package tn.esprit.devops_project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.*;

import org.assertj.core.api.Assertions;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest  {

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    InvoiceServiceImpl invoiceService;

    private Invoice invoice;
    private Operator operator;
    private Supplier supplier;

    @BeforeEach
    void setUp() {
        invoice = new Invoice();
        operator = new Operator();
        supplier = new Supplier();
    }

    @Test
    void retrieveAllInvoicesTest() {
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);
        invoices.add(new Invoice());
        Mockito.when(invoiceRepository.findAll()).thenReturn(invoices);

        List<Invoice> retrievedInvoices = invoiceService.retrieveAllInvoices();

        Assertions.assertThat(retrievedInvoices).isNotNull();
        Assertions.assertThat(retrievedInvoices.size()).isEqualTo(2);
        Mockito.verify(invoiceRepository).findAll();
    }
//



    @Test
    void cancelInvoiceTest() {
        Long invoiceId = 1L;
        invoice.setIdInvoice(invoiceId);

        // Mock the behavior of the repository
        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        doNothing().when(invoiceRepository).updateInvoice(invoiceId); // Assuming you have a custom method updateInvoice

        // Cancel the invoice
        invoiceService.cancelInvoice(invoiceId);

        Assertions.assertThat(invoice.getArchived()).isTrue(); // Check if the invoice is archived
        Mockito.verify(invoiceRepository).findById(invoiceId);
        Mockito.verify(invoiceRepository).updateInvoice(invoiceId);
    }

    @Test
    void cancelInvoiceWithNonexistentInvoiceTest() {
        Long nonExistentInvoiceId = 999L;

        // Mock the behavior of the repository to return an empty Optional
        Mockito.when(invoiceRepository.findById(nonExistentInvoiceId)).thenReturn(Optional.empty());

        // Attempt to cancel a non-existent invoice should throw an exception
        assertThrows(NullPointerException.class, () -> invoiceService.cancelInvoice(nonExistentInvoiceId));
    }
    @Test
    void retrieveInvoice() {
        invoice.setIdInvoice(1L);
        Mockito.when(invoiceRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(invoice));

        Invoice inv = invoiceService.retrieveInvoice(invoice.getIdInvoice());

        //Assertions.assertThat(oper).isNotNull();
        assertNotNull(inv);
        verify(invoiceRepository).findById(Mockito.anyLong());
    }

    @Test
    @Disabled
    void getInvoicesBySupplierTest() {
        Long supplierId = 1L;
        supplier.setIdSupplier(supplierId);
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        invoice1.setSupplier(supplier);
        invoice2.setSupplier(supplier);
        supplier.setInvoices(Set.of(invoice1, invoice2));

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        List<Invoice> invoices = invoiceService.getInvoicesBySupplier(supplierId);

        Assertions.assertThat(invoices).isNotNull();
        //Assertions.assertThat(invoices.size()).isEqualTo(2);
        Assertions.assertThat(invoices).containsExactly(invoice1, invoice2);

        verify(supplierRepository).findById(supplierId);
        verifyNoMoreInteractions(supplierRepository);
    }

    @Test
    void assignOperatorToInvoiceTest() {
        Long operatorId = 1L;
        Long invoiceId = 2L;
        operator.setIdOperateur(operatorId);
        invoice.setIdInvoice(invoiceId);

        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        System.out.println(invoice.getIdInvoice() +""+ operator.getIdOperateur());
        operator.setInvoices(new HashSet<>());
        invoiceService.assignOperatorToInvoice(operatorId, invoiceId);


        Assertions.assertThat(operator.getInvoices()).contains(invoice);
        verify(operatorRepository).findById(operatorId);
        verify(invoiceRepository).findById(invoiceId);
        verify(operatorRepository).save(operator);
    }


    @Test
    void getTotalAmountInvoiceBetweenDatesTest() {
        Date startDate = new Date(1635633600000L); // Replace with a valid start date
        Date endDate = new Date(1667169600000L);   // Replace with a valid end date
        float totalAmount = 100.0f; // Replace with the expected total amount

        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(totalAmount);

        float result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        Assertions.assertThat(result).isEqualTo(totalAmount);
        verify(invoiceRepository).getTotalAmountInvoiceBetweenDates(startDate, endDate);
    }

}
