class Electronique extends Produit{
    private int garantie;

    public Electronique(int id, String name, int quantity, int warranty, double prix) {
        super(id, name, quantity, prix);
        this.garantie = warranty;
    }

    public int getWarranty() {
        return garantie;
    }

    @Override
    public String toString() {
        return super.toString() + ", garantie=" + garantie + " ans";
    }
}
