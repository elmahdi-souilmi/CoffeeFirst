package application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.coffeemachine.controllers.ClientController;
import com.coffeemachine.controllers.MachineController;
import com.coffeemachine.controllers.ProductController;
import com.coffeemachine.controllers.TechnicianController;
import com.coffeemachine.models.Client;
import com.coffeemachine.models.Machine;
import com.coffeemachine.models.Product;
import com.coffeemachine.models.Technician;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController {
	// Techenicien
	@FXML
	private TextField idTecnicien;
	@FXML
	private TextField NameTecnicien;
	@FXML
	private TextField PhoneTecnicien;
	@FXML
	private TextField idRefTecnicien;
	@FXML
	private TextField AccescodeTecnicien;
	@FXML
	private TableView<Technician> TechenicienTab;
	@FXML
	private TableColumn<Technician, Long> idtech;
	@FXML
	private TableColumn<Technician, String> nametech;
	@FXML
	private TableColumn<Technician, String> phonetech;
	@FXML
	private TableColumn<Technician, String> idReftech;
	@FXML
	private TableColumn<Technician, String> accCodetech;
	
	// Product
	@FXML
	private TextField idProduit;
	@FXML
	private TextField NameProduit;
	@FXML
	private TextField PriceProduit;
	@FXML
	private TableView<Product> ProductTab;
	@FXML
	private TableColumn<Product, Long> idproduct;
	@FXML
	private TableColumn<Product, String> nameproduct;
	@FXML
	private TableColumn<Technician, String> priceproduct;
	//lists
	private ObservableList<String> productName = FXCollections.observableArrayList();
	private ObservableList<Technician> technicians = FXCollections.observableArrayList();
	private ObservableList<Product> products = FXCollections.observableArrayList();
	private ObservableList<Machine> machines = FXCollections.observableArrayList();
	private ObservableList<Client> clients = FXCollections.observableArrayList();
	private ObservableList<Product> selectedproducts = FXCollections.observableArrayList();
	private ObservableList<String> selectedproductsName = FXCollections.observableArrayList();
	// Machine
	@FXML
	private TextField idMachine;
	@FXML
	private TextField IsTurnedOnmachine;
	@FXML
	private TextField SumChangemachine;
	@FXML
	private ListView<String> list1machine;
	@FXML
	private ListView<String> list2machine;
	@FXML
	private TableView<Machine> MachineTab;
	@FXML
	private TableColumn<Machine, Long> idmachine;
	@FXML
	private TableColumn<Machine, Boolean> turnedON;
	@FXML
	private TableColumn<Machine, Double> sumchange;
	@FXML
	private TableColumn<Machine, ListView<Product>> listProduct;
	@FXML
	private ComboBox<Machine> listMAchine;
	// client
	@FXML
	private TextField idclient;
	@FXML
	private TextField nameClient;
	@FXML
	private TextField phoneClient;
	@FXML
	private TextField choiceClient;
	@FXML
	private TextField coinsClient;
	
	@FXML
	private TableView<Client> clientTab;
	@FXML
	private TableColumn<Client, Long> Idclient;
	@FXML
	private TableColumn<Client, String> name;
	@FXML
	private TableColumn<Client, String> Choice;
	@FXML
	private TableColumn<Client, String> phone;
	@FXML
	private TableColumn<Client, Double> Coins;
	// buy 
	@FXML
	private TableColumn<Product, Long> idproductbuy;
	@FXML
	private TableColumn<Product, String> nameproductbuy;
	@FXML
	private TableColumn<Technician, String> priceproductbuy;
	@FXML 
	private ComboBox<String> machinesNames;
	// Add techenicien 
	public void AddTechenicienButtonClicked() throws Exception {
		 Technician technician = new Technician(Long. parseLong(idTecnicien.getText()),NameTecnicien.getText(),
		       PhoneTecnicien.getText(),idRefTecnicien.getText(),AccescodeTecnicien.getText());
		 
         TechnicianController technicianController = new TechnicianController();
         technicianController.AddTechnician(technicians, technician);
         
         idtech.setCellValueFactory(new PropertyValueFactory<>("id"));
         nametech.setCellValueFactory(new PropertyValueFactory<>("name"));
         phonetech.setCellValueFactory(new PropertyValueFactory<>("phone"));
         idReftech.setCellValueFactory(new PropertyValueFactory<>("idRef"));
         accCodetech.setCellValueFactory(new PropertyValueFactory<>("accessCode"));
         
         TechenicienTab.setItems(technicians);
         
		 
	}
	// Add product
	public void AddProductButtonClicked() throws Exception {
		productName.clear();
		Product product = new Product(Long. parseLong(idProduit.getText()),NameProduit.getText(),
				Double. parseDouble(PriceProduit.getText()));
		ProductController productController = new ProductController();
		productController.AddProduct(products, product);
		idproduct.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameproduct.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceproduct.setCellValueFactory(new PropertyValueFactory<>("price"));
		ProductTab.setItems(products);
		
		for (Product product_name:products ) {
			productName.add(product_name.getName());
		}
		list1machine.setItems(productName);
		}
	// Add machine
	
	public void setList2Data() { 
		String nameProdect = list1machine.getSelectionModel().getSelectedItem();

		for (Product selectedProduct:products ) {
			if (nameProdect == selectedProduct.getName()) {
				 selectedproducts.add(selectedProduct);
				 selectedproductsName.add(selectedProduct.getName());
				 productName.remove(nameProdect);
				break;
			}
			
		}
		list2machine.setItems(selectedproductsName);
		list1machine.setItems(productName);
		
			
	}
	
	public void AddMachineButtonClicked() throws Exception {
		List<Product> list = selectedproducts.stream().collect(Collectors.toList());
		Machine machine = new Machine(Long. parseLong(idMachine.getText()), Boolean.parseBoolean(IsTurnedOnmachine.getText()) ,
				Double. parseDouble(SumChangemachine.getText()), list);
		MachineController machineController = new MachineController();
		machineController.AddMachine(machines, machine);
		idmachine.setCellValueFactory(new PropertyValueFactory<>("id"));
		turnedON.setCellValueFactory(new PropertyValueFactory<>("isTurnedOn"));
		sumchange.setCellValueFactory(new PropertyValueFactory<>("sumChange"));
		listProduct.setCellValueFactory(new PropertyValueFactory<>("products"));
		MachineTab.setItems(machines);
		for (Product product_name:products ){ {
			productName.add(product_name.getName());
		}
		list1machine.setItems(productName);
		};
		list2machine.getItems().clear();
		}
	public void AddClienButtonClicked() throws Exception {
	
		Client client= new Client(Long. parseLong(idclient.getText()), nameClient.getText(),
				phoneClient.getText(),choiceClient.getText(),Double. parseDouble(coinsClient.getText()));
		ClientController clientController = new ClientController();
		clientController.AddClient(clients, client);
		Idclient.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		Choice.setCellValueFactory(new PropertyValueFactory<>("choice"));
		Coins.setCellValueFactory(new PropertyValueFactory<>("coins"));
		clientTab.setItems(clients);
		
		}
	public void RunMachineButtonClicked() throws Exception {
		

		
		}
}
