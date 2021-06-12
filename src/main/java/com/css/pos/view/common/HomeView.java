package com.css.pos.view.common;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@SessionScoped
public class HomeView {
	
	public String logout() {
		System.out.println("logging out.......");
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	private DashboardModel board;
	@PostConstruct
	public void init() {
		board = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
         
        column1.addWidget("reports");
                
        column2.addWidget("notifications");
        column2.addWidget("todo");
         
        column3.addWidget("msgs");
 
        board.addColumn(column1);
        board.addColumn(column2);
        board.addColumn(column3);
	}

	public DashboardModel getBoard() {
		return board;
	}

	public void setBoard(DashboardModel board) {
		this.board = board;
	}
	
}
