class Vestimentaire extends Produit{
    private String taille;

    public Vestimentaire(int id, String name, int quantity, String size, double prix) {
        super(id, name, quantity, prix);
        this.taille = size;
    }

    public String getSize() {
        return taille;
    }

    @Override
    public String toString() {
        return super.toString() + ", taille=" + taille;
    }
}
