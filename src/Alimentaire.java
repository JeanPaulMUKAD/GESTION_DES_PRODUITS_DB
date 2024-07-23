class Alimentaire extends Produit{
    private String Dateexpiration;

    public Alimentaire(int id, String name, int quantity, String expirationDate, double prix) {
        super(id, name, quantity, prix);
        this.Dateexpiration = expirationDate;
    }

    public String getExpirationDate() {
        return Dateexpiration;
    }

    @Override
    public String toString() {
        return super.toString() + ", date d'expiration=" + Dateexpiration;
    }
}
