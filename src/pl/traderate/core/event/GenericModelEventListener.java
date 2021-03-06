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

package pl.traderate.core.event;

/**
 * An interface implemented by all model event listeners.
 */
public interface GenericModelEventListener {

	public void visitModelEvent(GenericModelEvent e);

	public void handleModelEvent(GenericModelEvent e);

	public void handleModelEvent(JournalUpdatedModelEvent e);

	public void handleModelEvent(QuoteUpdatedModelEvent e);

	public void handleModelEvent(NodesUpdatedModelEvent e);

	public void handleModelEvent(JournalClosedModelEvent e);

	public void handleModelEvent(JournalCreatedModelEvent e);

	public void handleModelEvent(JournalOpenedModelEvent e);

	public void handleModelEvent(JournalSavedModelEvent e);
}
