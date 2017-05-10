package legacy;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.PeriodicTransaction;
import model.Transaction;
import javafx.event.ActionEvent;

/**
 * @author user
 *
 */
public class PeriodicController /*extends AccountSelector */{
//
//	@FXML
//	TableView<PeriodicTransaction> viewPeriodic;
//	List<Transaction> transactionsOwned;
//	List<PeriodicTransaction> displayedPTs;
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		super.initialize(location, resources);
//
//		this.transactionsOwned = new ArrayList<>();
//		
//		this.displayedPTs = new ArrayList<>();
//		this.viewPeriodic.setItems(FXCollections.observableArrayList(this.displayedPTs));
//		
//		
//
//	}
//
//	@FXML
//	public void accountChosen(ActionEvent event) {
//		if(this.accountCombo.getValue()==null){
//			return;
//		}
//		//get an account specific subset
//		
//		this.transactionsOwned = this.accountCombo.getValue().getTransactions();
//		//the set disallow duplicates
//		HashSet<PeriodicTransaction >pts = new HashSet<>();
//		for (Transaction t : this.transactionsOwned) {
//			if(t.getPeriodicTransaction()!=null){
//				pts.add(t.getPeriodicTransaction());
//			}
//				
//		}
//		this.displayedPTs.clear();
//		this.displayedPTs.addAll(pts);
//		this.viewPeriodic.setItems(FXCollections.observableArrayList(this.displayedPTs));
//		
//	}

}
