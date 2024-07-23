import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Magasin magasin = new Magasin();
    private static Map<Integer, Produit> productMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            displayMenu();
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    ajouterProduit(scanner);
                    break;
                case 2:
                    supprimerProduit(scanner);
                    break;
                case 3:
                    modifierProduit(scanner);
                    break;
                case 4:
                    modifierQuantiteProduit(scanner);
                    break;
                case 5:
                    rechercherProduitParNom(scanner);
                    break;
                case 6:
                    listerProduitParLettre(scanner);
                    break;
                case 7:
                    afficherNombreProduitEnStock();
                    break;
                case 8:
                    afficherNombreProduitParType();
                    break;
                case 9:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
            System.out.println();
        } while (choix != 9);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Gestion des produits :");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Supprimer un produit");
        System.out.println("3. Modifier un produit");
        System.out.println("4. Modifier la quantité d'un produit");
        System.out.println("5. Rechercher un produit par nom");
        System.out.println("6. Lister les produits par lettre");
        System.out.println("7. Afficher le nombre de produits en stock");
        System.out.println("8. Afficher le nombre de produits par type");
        System.out.println("9. Quitter");
        System.out.print("Entrez votre choix : ");
    }

    private static void ajouterProduit(Scanner scanner) {
        System.out.print("Entrez le type de produit (Electronique/Alimentaire/Vestimentaire) : ");
        String type = scanner.nextLine();

        switch (type.toLowerCase()) {
            case "electronique":
                addElectronicProduct(scanner);
                break;
            case "alimentaire":
                addFoodProduct(scanner);
                break;
            case "vestimentaire":
                addClothingProduct(scanner);
                break;
            default:
                System.out.println("Type de produit invalide. Veuillez réessayer.");
                break;
        }
    }

    private static void addElectronicProduct(Scanner scanner) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO produits_electroniques (id, nom, quantite, garantie, prix) VALUES (?, ?, ?, ?, ?)")) {

            System.out.print("Entrez l'identifiant du produit : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (productMap.containsKey(id)) {
                System.out.println("Ce produit existe déjà. Veuillez entrer un nouvel identifiant.");
                return;
            }

            System.out.print("Entrez le nom du produit : ");
            String name = scanner.nextLine();
            System.out.print("Entrez la quantité en stock : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Entrez la garantie (en années) : ");
            int garantie = scanner.nextInt();
            System.out.print("Entrez le prix du produit : ");
            double prix = scanner.nextDouble();
            scanner.nextLine();

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, garantie);
            preparedStatement.setDouble(5, prix);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Electronique product = new Electronique(id, name, quantity, garantie, prix);
                magasin.addProduct(product);
                System.out.println("Produit électronique ajouté avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout du produit électronique.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private static void addFoodProduct(Scanner scanner) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO produits_alimentaires (id, nom, quantite, dateexpiration, prix) VALUES (?, ?, ?, ?, ?)")) {

            System.out.print("Entrez l'identifiant du produit : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (productMap.containsKey(id)) {
                System.out.println("Ce produit existe déjà. Veuillez entrer un nouvel identifiant.");
                return;
            }

            System.out.print("Entrez le nom du produit : ");
            String name = scanner.nextLine();
            System.out.print("Entrez la quantité en stock : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Entrez la date d'Expiration : ");
            String dateExpiration = scanner.nextLine();
            System.out.print("Entrez le prix du produit : ");
            double prix = scanner.nextDouble();
            scanner.nextLine();

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4, dateExpiration);
            preparedStatement.setDouble(5, prix);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Alimentaire product = new Alimentaire(id, name, quantity, dateExpiration, prix);
                magasin.addProduct(product);
                System.out.println("Produit alimentaire ajouté avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout du produit alimentaire.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private static void addClothingProduct(Scanner scanner) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO produits_vestimentaires (id, nom, quantite, taille, prix) VALUES (?, ?, ?, ?, ?)")) {

            System.out.print("Entrez l'identifiant du produit : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (productMap.containsKey(id)) {
                System.out.println("Ce produit existe déjà. Veuillez entrer un nouvel identifiant.");
                return;
            }

            System.out.print("Entrez le nom du produit : ");
            String name = scanner.nextLine();
            System.out.print("Entrez la quantité en stock : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Entrez la taille de l'habit : ");
            String taille = scanner.nextLine();
            System.out.print("Entrez le prix du produit : ");
            double prix = scanner.nextDouble();
            scanner.nextLine();

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4, taille);
            preparedStatement.setDouble(5, prix);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Vestimentaire product = new Vestimentaire(id, name, quantity, taille, prix);
                magasin.addProduct(product);
                System.out.println("Produit vestimentaire ajouté avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout du produit vestimentaire.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private static void supprimerProduit(Scanner scanner) {
        System.out.print("Entrez l'identifiant du produit à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM produits WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                if (magasin.removeProduct(id)) {
                    System.out.println("Produit supprimé avec succès.");
                } else {
                    System.out.println("Produit non trouvé dans la mémoire du programme, mais supprimé de la base de données.");
                }
            } else {
                System.out.println("Produit non trouvé dans la base de données.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private static void modifierProduit(Scanner scanner) {
        System.out.print("Entrez l'identifiant du produit à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produit product = magasin.getProductById(id);
        if (product != null) {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE produits SET nom = ?, quantite = ?, prix = ? WHERE id = ?")) {

                System.out.print("Entrez le nouveau nom du produit : ");
                String newName = scanner.nextLine();
                System.out.print("Entrez la nouvelle quantité en stock : ");
                int newQuantity = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Entrez le nouveau prix du produit : ");
                double newPrix = scanner.nextDouble();
                scanner.nextLine();

                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, newQuantity);
                preparedStatement.setDouble(3, newPrix);
                preparedStatement.setInt(4, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    product.setName(newName);
                    product.setQuantity(newQuantity);
                    product.setPrix(newPrix);
                    System.out.println("Produit mis à jour avec succès.");
                } else {
                    System.out.println("Erreur lors de la mise à jour du produit.");
                }

            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
            }
        } else {
            System.out.println("Produit non trouvé.");
        }
    }

    private static void modifierQuantiteProduit(Scanner scanner) {
        System.out.print("Entrez l'identifiant du produit dont vous voulez modifier la quantité : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produit product = magasin.getProductById(id);
        if (product != null) {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE produits SET quantite = ? WHERE id = ?")) {

                System.out.print("Entrez la nouvelle quantité en stock : ");
                int newQuantity = scanner.nextInt();
                scanner.nextLine();

                preparedStatement.setInt(1, newQuantity);
                preparedStatement.setInt(2, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    product.setQuantity(newQuantity);
                    System.out.println("Quantité du produit mise à jour avec succès.");

                } else {
                    System.out.println("Erreur lors de la mise à jour de la quantité du produit.");
                }

            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
            }
        } else {
            System.out.println("Produit non trouvé.");
        }
    }

    private static void rechercherProduitParNom(Scanner scanner) {
        System.out.print("Entrez le nom du produit à rechercher : ");
        String name = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM produits WHERE nom LIKE ?")) {

            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Produit> foundProducts = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int quantite = resultSet.getInt("quantite");
                double prix = resultSet.getDouble("prix");

                // Ajouter le produit à la liste des produits trouvés
                Produit product = new Produit(id, nom, quantite, prix);
                foundProducts.add(product);
            }

            if (foundProducts.isEmpty()) {
                System.out.println("Aucun produit trouvé avec ce nom.");
            } else {
                for (Produit product : foundProducts) {
                    System.out.println(product);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private static void listerProduitParLettre(Scanner scanner) {
        System.out.print("Entrez la lettre pour lister les produits : ");
        char letter = scanner.nextLine().charAt(0);

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM produits WHERE nom LIKE ?")) {

            preparedStatement.setString(1, letter + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Produit> foundProducts = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int quantite = resultSet.getInt("quantite");
                double prix = resultSet.getDouble("prix");

                // Ajouter le produit à la liste des produits trouvés
                Produit product = new Produit(id, nom, quantite, prix);
                foundProducts.add(product);
            }

            if (foundProducts.isEmpty()) {
                System.out.println("Aucun produit trouvé commençant par cette lettre.");
            } else {
                for (Produit product : foundProducts) {
                    System.out.println(product);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private static void afficherNombreProduitEnStock() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT SUM(quantite) AS total FROM produits")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                System.out.println("Nombre de produits en stock : " + total);
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    private static void afficherNombreProduitParType() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(
                     "SELECT COUNT(*) AS total FROM produits_electroniques");
             PreparedStatement preparedStatement2 = connection.prepareStatement(
                     "SELECT COUNT(*) AS total FROM produits_alimentaires");
             PreparedStatement preparedStatement3 = connection.prepareStatement(
                     "SELECT COUNT(*) AS total FROM produits_vestimentaires")) {

            ResultSet resultSet1 = preparedStatement1.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            ResultSet resultSet3 = preparedStatement3.executeQuery();

            if (resultSet1.next() && resultSet2.next() && resultSet3.next()) {
                int electroniqueCount = resultSet1.getInt("total");
                int alimentaireCount = resultSet2.getInt("total");
                int vestimentaireCount = resultSet3.getInt("total");

                System.out.println("Nombre de produits par type :");
                System.out.println("Alimentaire : " + alimentaireCount);
                System.out.println("Électronique : " + electroniqueCount);
                System.out.println("Vestimentaire : " + vestimentaireCount);
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }
}