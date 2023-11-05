package tn.esprit.devops_project;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OperatorServiceImplTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        // Mock the behavior of operatorRepository.findAll()
        List<Operator> operators = new ArrayList<>();
        when(operatorRepository.findAll()).thenReturn(operators);

        List<Operator> result = operatorService.retrieveAllOperators();

        // Assert the result
        // Add your assertions here
    }

    @Test
    public void testAddOperator() {
        // Mock the behavior of operatorRepository.save()
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.addOperator(operator);

        // Assert the result
        // Add your assertions here
    }

    @Test
    public void testDeleteOperator() {
        Long operatorId = 1L;

        // Mock the behavior of operatorRepository.deleteById()
        doNothing().when(operatorRepository).deleteById(operatorId);

        operatorService.deleteOperator(operatorId);

        // Assert any post-delete conditions or verify the interactions
    }

    @Test
    public void testUpdateOperator() {
        Operator operator = new Operator();

        // Mock the behavior of operatorRepository.save()
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.updateOperator(operator);

        // Assert the result
        // Add your assertions here
    }

    @Test
    public void testRetrieveOperator() {
        Long operatorId = 1L;
        Operator operator = new Operator();

        // Mock the behavior of operatorRepository.findById()
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        Operator result = operatorService.retrieveOperator(operatorId);

        // Assert the result
        // Add your assertions here
    }
}
