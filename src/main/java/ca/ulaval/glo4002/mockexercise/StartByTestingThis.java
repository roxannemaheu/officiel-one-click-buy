package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.*;

import java.util.ArrayList;
import java.util.List;

public class StartByTestingThis {

    private InvoiceFactory invoiceFactory;
    private CartFactory cartFactory;
    private ProductRepository productRepository;
    private Cart cart;
    private Invoice invoice;

    public StartByTestingThis(CartFactory cartFactory, InvoiceFactory invoiceFactory, ProductRepository productRepository, Cart cart) {
        this.cartFactory = cartFactory;
        this.invoiceFactory = invoiceFactory;
        this.productRepository = productRepository;
        this.cart = cart;
    }

    public Invoice oneClickBuy(String clientEmail, String productSku){
        // Étape 1 : Créer le cart avec le CartFactory
        Cart cart = cartFactory.create(clientEmail);

        // Étape 2 : Trouver le produit avec le ProductRepository
        Product currentProduct = productRepository.findBySku(productSku);

        // Étape 3 : Ajouter le produit au cart
        cart.addProduct(currentProduct);

        // Étape 4 : Pour chaque item du cart, ajouter une ligne sur l'invoice
        invoice = invoiceFactory.create(clientEmail,cart.getProducts());

        // Étape 5 : Retourner l'invoice
        return invoice;
    }
}
