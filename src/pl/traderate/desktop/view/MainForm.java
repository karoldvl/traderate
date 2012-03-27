/*
 * Copyright (C) 2012 Karol Piczak <karol@dvl.pl>
 *
 * This file is part of the TradeRate package.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package pl.traderate.desktop.view;

import javax.swing.*;
import java.awt.*;

public class MainForm extends GenericForm {

	MainView view;
	
	JFrame frame;

	JPanel root;

	private JPanel statusbar;

	private JPanel toolbar;

	private JPanel mainPanel;

	private JPanel navigationPanel;

	private JProgressBar progressBar;

	private JList tagList;

	private JTree navigationTree;

	private JTabbedPane tabs;

	private JPanel sidebar;

	private JLabel topNavigationLabel;

	MainForm(GenericView view) {
		super(view);
		this.view = (MainView) super.view;

		frame = new JFrame("Main");
		frame.setMinimumSize(new Dimension(300, 300));
		frame.setContentPane(root);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

	//	buttonOK.addActionListener(this.view.new OnButtonOKClicked());
	}

	void show() {
		frame.setVisible(true);

	}

	private void createUIComponents() {
		statusbar = new JPanel();
		statusbar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UIManager.getDefaults().getColor("mid")));
		
		tabs = new JTabbedPane();
		tabs.addTab("Podsumowanie", new SummaryForm().root);
	}
}
