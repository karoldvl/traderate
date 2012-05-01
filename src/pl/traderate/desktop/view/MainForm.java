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

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultComboBoxRenderer;
import org.pushingpixels.substance.api.skin.SkinInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainForm extends GenericForm {

	MainView view;
	
	GenericView summaryView;

	GenericView journalView;
	
	JFrame frame;

	JPanel root;

	JPanel statusbar;

	JPanel toolbar;

	JPanel mainPanel;

	JPanel navigationPanel;

	JProgressBar progressBar;

	JTree navigationTree;

	JTabbedPane tabs;

	JLabel topNavigationLabel;

	JButton manageTreeButton;

	JLabel versionText;

	JComboBox changeSkin;

	MainForm(GenericView view, GenericView summaryView, GenericView journalView) {
		super(view);
		this.view = (MainView) super.view;

		this.summaryView = summaryView;
		this.journalView = journalView;

		frame = new JFrame("Main");
		frame.setMinimumSize(new Dimension(640, 550));
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setContentPane(root);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		manageTreeButton.addActionListener(this.view.new OnManageButtonClicked());
	}

	void show() {
		frame.setVisible(true);

	}

	private void createUIComponents() {
		statusbar = new JPanel();
		statusbar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UIManager.getDefaults().getColor("mid")));
		
		tabs = new JTabbedPane();
		tabs.addTab("Podsumowanie", ((SummaryForm) summaryView.getForm()).root);
		tabs.addTab("Dziennik", ((JournalForm) journalView.getForm()).root);

		navigationTree = new JTree();

		changeSkin = new SubstanceSkinComboSelector();
	}

	public class SubstanceSkinComboSelector extends JComboBox {
		public SubstanceSkinComboSelector() {
			// populate the combobox
			super(new ArrayList<SkinInfo>(SubstanceLookAndFeel.getAllSkins().values()).toArray());
			// set the current skin as the selected item
			SubstanceSkin currentSkin = SubstanceLookAndFeel.getCurrentSkin();
			for (SkinInfo skinInfo : SubstanceLookAndFeel.getAllSkins().values()) {
				if (skinInfo.getDisplayName().compareTo(
						currentSkin.getDisplayName()) == 0) {
					this.setSelectedItem(skinInfo);
					break;
				}
			}
			// set custom renderer to show the skin display name
			this.setRenderer(new SubstanceDefaultComboBoxRenderer(this) {
				@Override
				public Component getListCellRendererComponent(JList list,
				                                              Object value, int index, boolean isSelected,
				                                              boolean cellHasFocus) {
					return super.getListCellRendererComponent(list,
							((SkinInfo) value).getDisplayName(), index, isSelected,
							cellHasFocus);
				}
			});
			// add an action listener to change skin based on user selection
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							SubstanceLookAndFeel
									.setSkin(((SkinInfo) SubstanceSkinComboSelector.this
											.getSelectedItem()).getClassName());
						}
					});
				}
			});
		}
	}

	public JFrame getFrame() {
		return frame;
	}
}
