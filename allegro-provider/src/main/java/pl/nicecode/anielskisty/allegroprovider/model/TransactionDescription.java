package pl.nicecode.anielskisty.allegroprovider.model;

/**
 * Created by jhojczak on 4/10/15.
 */
public enum TransactionDescription {
    CREATE(1,"utworzenie aktu zakupowego"),
    FILL_DATA(1,"utworzenie formularza pozakupowego"),
    CANCELD(1,"anulowanie formularza pozakupowego"),
    FINALIZED(1,"zakończenie");

    public final String description;
    public final int id;

    TransactionDescription(int id, String description){
        this.description=description;
        this.id=id;
    }
    public static TransactionDescription valueOfId(int i){
        switch (i){
            case 1: return TransactionDescription.CREATE;
            case 2: return TransactionDescription.FILL_DATA;
            case 3: return TransactionDescription.CANCELD;
            case 4: return TransactionDescription.FINALIZED;
            default:
                throw new IllegalArgumentException("Transaction type is in range 1-4");
        }
    }

}
