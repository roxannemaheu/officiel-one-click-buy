package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StartByTestingThisTest {

    private static final String CLIENT_EMAIL = "abc@def.com";
    private static final String PRODUCT_SKU = "123";

    @Mock
    private CartFactory cartFactory;
    @Mock
    private InvoiceFactory invoiceFactory;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Cart cart;
    @Mock
    private Product product;
    @Mock
    private Invoice invoice;

    @InjectMocks
    private StartByTestingThis service;

    @BeforeEach
    public void setUp() {
        given(cart.getProducts()).willReturn(List.of(product));
        given(cartFactory.create(CLIENT_EMAIL)).willReturn(cart);
        given(invoiceFactory.create(CLIENT_EMAIL, cart.getProducts())).willReturn(invoice);
        given(productRepository.findBySku(PRODUCT_SKU)).willReturn(product);

        service = new StartByTestingThis(cartFactory, invoiceFactory, productRepository, cart);
    }

    @Test
    public void WhenUsingOneClickBuy_ThenCreatesCart() {
        service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);

        verify(cartFactory).create(CLIENT_EMAIL);
    }

    @Test
    public void WhenUsingOneClickBuy_ThenFindProductWithProductRepository() {
        service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);

        verify(productRepository).findBySku(PRODUCT_SKU);
    }

    @Test
    public void WhenUsingOneClickBuy_ThenProductIsAddedToCart() {
        service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);

        verify(cart).addProduct(product);
    }

    @Test
    public void WhenUsingOneClickBuy_AddALineOnInvoicePerItemInCart() {
        service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);

        verify(invoiceFactory).create(CLIENT_EMAIL, cart.getProducts());
    }

    @Test
    public void WhenUsingOneClickBuy_ReturnInvoice() {
        Invoice invoice = service.oneClickBuy(CLIENT_EMAIL, PRODUCT_SKU);

        assertNotNull(invoice);
    }
}
