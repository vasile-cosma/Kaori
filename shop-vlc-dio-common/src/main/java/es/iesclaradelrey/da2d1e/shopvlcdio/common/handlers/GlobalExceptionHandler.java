package es.iesclaradelrey.da2d1e.shopvlcdio.common.handlers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ProblemDetail clientNotFound(ClientNotFoundException e){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pd.setTitle("Cliente no encontrado");
        return pd;
    }
    @ExceptionHandler(InsufficientStockException.class)
    public ProblemDetail insufficientStock(InsufficientStockException e){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        pd.setTitle("Stock insuficiente");
        return pd;
    }
    @ExceptionHandler(BrandNotFoundException.class)
    public ProblemDetail brandNotFoudn(BrandNotFoundException e){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pd.setTitle("Marca no encontrada");
        return pd;
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ProblemDetail clientNotFound(CategoryNotFoundException e){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pd.setTitle("Categoría no encontrada");
        return pd;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail productNotFound(ProductNotFoundException e){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pd.setTitle("Producto no encontrado");
        return pd;
    }
    @ExceptionHandler(InvalidUnitsException.class)
    public ProblemDetail invalidUnits(InvalidUnitsException e){
            ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
            pd.setTitle("Debes introducir unidades válidas (>0)");
            return pd;
        }
    @ExceptionHandler(ProductNotInCartException.class)
    public ProblemDetail productNotInCard(ProductNotInCartException e){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        pd.setTitle("Este producto no se encuentra en el carrito");
        return pd;
    }
}
