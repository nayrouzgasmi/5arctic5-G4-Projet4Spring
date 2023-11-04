package tn.esprit.devops_project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceServiceImplMock {
    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testRetrieveAllInvoices() {
        List<Invoice> invoices = List.of(new Invoice(), new Invoice());
        Mockito.when(invoiceRepository.findAll()).thenReturn(invoices);

        List<Invoice> result = invoiceService.retrieveAllInvoices();

        assertEquals(2, result.size());
    }
    @Test
    public void testCancelInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        invoiceService.cancelInvoice(invoiceId);

        verify(invoiceRepository).save(invoice);
        assertEquals(true, invoice.getArchived());
    }

    @Test
    public void testRetrieveInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        Invoice result = invoiceService.retrieveInvoice(invoiceId);

        assertEquals(invoice, result);
    }


    @Test
    public void testGetInvoicesBySupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        supplier.getInvoices().add(invoice1);
        supplier.getInvoices().add(invoice2);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        List<Invoice> result = invoiceService.getInvoicesBySupplier(supplierId);

        assertEquals(2, result.size());
    }

    @Test
    public void testAssignOperatorToInvoice() {
        Long operatorId = 1L;
        Long invoiceId = 2L;
        Operator operator = new Operator();
        Invoice invoice = new Invoice();
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        invoiceService.assignOperatorToInvoice(operatorId, invoiceId);

        verify(operatorRepository).save(operator);
        assertEquals(1, operator.getInvoices().size());
        assertEquals(invoice, operator.getInvoices());
    }

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        Date startDate = new Date();
        Date endDate = new Date();
        double totalAmount = 1000.0; // You can set this to the expected total amount
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn((float) totalAmount);

        double result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        assertEquals(totalAmount, result);
    }
}

