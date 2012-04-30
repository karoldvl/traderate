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

import com.michaelbaranov.microba.calendar.DatePicker;

import javax.swing.*;

public class JournalForm extends GenericForm {

	JPanel root;

	JTable entries;

	JTabbedPane entryCreator;

	JSpinner spinner1;

	JComboBox comboBox1;

	JComboBox comboBox2;

	JComboBox comboBox3;

	DatePicker datePicker1;

	JButton submitButton;

	JPanel equityEntryCreatorTab;

	JPanel cashEntryCreatorTab;

	JPanel allocationEntryCreatorTab;

	JPanel nodesCreatorTab;

	public JournalForm(GenericView view) {
		super(view);
		this.view = (JournalView) super.view;
	}

	void show() {

	}

	private void createUIComponents() {
		entries = new JTable();
	}
}
