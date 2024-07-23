class Produit {
    private int id;
    private String name;
    private int quantity;
    private double prix;
    public Produit(int id, String name, int quantity, double prix) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.prix = prix;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
    public double getPrix() {
        return prix;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public  void  setPrix(Double prix){
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit [id=" + id + ", nom=" + name + ", prix="+ prix+", quantité=" + quantity + "]";
    }
}
