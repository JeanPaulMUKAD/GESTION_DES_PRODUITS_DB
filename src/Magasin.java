import java.util.ArrayList;

class Magasin {
    private ArrayList<Produit> products = new ArrayList<>();

    public void addProduct(Produit product) {
        products.add(product);
    }

    public boolean removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Produit getProductById(int id) {
        for (Produit product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    public ArrayList<Produit> searchProductByName(String name) {
        ArrayList<Produit> foundProducts = new ArrayList<>();
        for (Produit product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    public ArrayList<Produit> listProductsByLetter(char letter) {
        ArrayList<Produit> foundProducts = new ArrayList<>();
        for (Produit product : products) {
            if (product.getName().toLowerCase().startsWith(String.valueOf(letter).toLowerCase())) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    public int getProductCountByType(String type) {
        int count = 0;
        for (Produit product : products) {
            if (product instanceof Alimentaire && type.equals("Alimentaire")) {
                count++;
            } else if (product instanceof Electronique && type.equals("Électronique")) {
                count++;
            } else if (product instanceof Vestimentaire && type.equals("Vestimentaire")) {
                count++;
            }
        }
        return count;
    }


    public int getProductCount() {
        return products.size();
    }
}
