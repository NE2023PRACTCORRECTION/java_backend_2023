package pract.oop_java.pms.v1.services;

import pract.oop_java.pms.v1.dto.requests.CreatePurchaseDTO;

import pract.oop_java.pms.v1.models.Product;
import pract.oop_java.pms.v1.models.Purchase;

import java.util.List;
import java.util.UUID;

public interface PurchaseService {

    Purchase createPurchase(CreatePurchaseDTO purchaseDTO);
    List<Purchase> getAllPurchases ();

    Purchase getPurchaseById(UUID id);

    Purchase updatePurchase(CreatePurchaseDTO purchaseDTO,UUID id);

    Purchase deletePurchaseById(UUID id);

    Purchase getPurchaseByproduct_code(String code);

    Purchase findPurchaseByProduct_code(String code);


}
