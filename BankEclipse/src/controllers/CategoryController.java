/**
 *@Author: Lucien Boulet Roblin 
 *3 mai 2017
 */
package controllers;

import java.net.URL;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.fxml.Initializable;
import model.Account;
import model.Category;
import model.Owner;
import model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

/**
 * @author Mathieu Traparic
 *
 */
public class CategoryController implements Initializable, Refreshable {

	@FXML
	TableView<SimpleEntry<Category, Integer>> categoryView;
	@FXML
	TableColumn<Category, String> descriptionCol;
	@FXML
	TableColumn<Category, Integer> countCol;
	@FXML PieChart pieChart;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.refresh();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.Refreshable#refresh()
	 */
	@Override
	public void refresh() {
		Owner loggedOwner = VistaNavigator.getInstance().getLoggedOwner();

		EntityManager em = VistaNavigator.getEmf().createEntityManager();

		// get the accounts from logged owner
		HashSet<Account> accountsOwned = new HashSet<>();
		TypedQuery<List> q = em.createQuery("SELECT o.accounts FROM Owner o WHERE o=:loggedOwner", List.class);
		accountsOwned
				.addAll((Collection<? extends Account>) q.setParameter("loggedOwner", loggedOwner).getResultList());

		// getResultist return null if no rows are found
		if (accountsOwned.contains(null) && accountsOwned.size() == 1) {
			accountsOwned.clear();
		}
		HashSet<Transaction> transactionOwned = new HashSet<>();
		for (Account account : accountsOwned) {
			transactionOwned.addAll(account.getTransactions());
		}

		HashSet<SimpleEntry<Category, Integer>> categoryCounts = new HashSet<>();
		Category other = new Category("Other");
		for (Transaction transaction : transactionOwned) {
			Category c = transaction.getCategory();
			if (c == null) {
				c = other;
			}
			categoryCounts.add(new SimpleEntry<Category, Integer>(c, 0));
		}
		transactionOwned.forEach(t -> {
			for (SimpleEntry<Category, Integer> simpleEntry : categoryCounts) {
				if (simpleEntry.getKey().equals(t.getCategory())
						|| (t.getCategory() == null && simpleEntry.getKey().getDescription().equals("Other"))) {
					simpleEntry.setValue(simpleEntry.getValue() + 1);
				}
			}
		});

		this.categoryView.getItems().addAll(categoryCounts);
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		double size = transactionOwned.size();
		for (final SimpleEntry<Category, Integer> entry : categoryCounts) {
			pieChartData.add(new Data(entry.getKey().getDescription(), entry.getValue()*100/size));
		}
		this.pieChart.setData(pieChartData);
		
		for (PieChart.Data data : pieChartData) {
			data.setName(data.getName()+" "+ String.valueOf(data.getPieValue()) + "%");
		    data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		            @Override public void handle(MouseEvent e) {
		            	//data.setName(data.getName()+" "+ String.valueOf(data.getPieValue()) + "%");
		             }
		        });
		}
		
		

	}

}
