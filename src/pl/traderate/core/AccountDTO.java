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

package pl.traderate.core;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * An account Data Transfer Object.
 */
public class AccountDTO {

	/**
	 * Unique ID of this account.
	 */
	public final int ID;

	/**
	 * Account name.
	 */
	public final String name;

	/**
	 * Physical cash deposited on this account.
	 */
	public final BigDecimal cashBalance;

	/**
	 * The virtual amount of cash that has not been allocated.
	 */
	public final BigDecimal unallocatedCash;

	/**
	 * Current market value of account holdings.
	 */
	public final BigDecimal currentValue;

	/**
	 * Value of account holdings in open prices (cost basis).
	 */
	public final BigDecimal openValue;

	/**
	 * Unrealized gain from account holdings.
	 */
	public final BigDecimal paperGain;

	/**
	 * Profit realized on account holdings.
	 */
	public final BigDecimal realizedGain;

	/**
	 * Income realized on account transactions.
	 */
	public final BigDecimal realizedIncome;

	/**
	 * Cost basis of realized transactions.
	 */
	public final BigDecimal realizedCost;

	/**
	 * Aggregate account value.
	 *
	 * Includes current market value of holdings and cash balance.
	 */
	public final BigDecimal value;

	/**
	 * Aggregate change in value.
	 *
	 * Includes unrealized and realized gains.
	 */
	public final BigDecimal valueChange;

	/**
	 * Holdings of this account in a DTO form.
	 */
	public final HoldingsDTO holdings;

	/**
	 * History of journal entries for this account.
	 */
	public final ArrayList<EntryDTO> entries;

	/**
	 * Cash allocations of this account in a DTO form.
	 */
	public final AccountCashAllocationsDTO cashAllocationsDTO;

	/**
	 * Creates a DTO of a given account object.
	 *
	 * @param account               Account object
	 * @param cashAllocationsDTO    Cash allocation DTO for a given account
	 */
	AccountDTO(Account account, AccountCashAllocationsDTO cashAllocationsDTO) {
		this.ID = account.getID();
		this.name = account.getName();
		this.cashBalance = account.getCashBalance().setScale(2);
		this.unallocatedCash = account.getUnallocatedCash().setScale(2);
		this.holdings = new HoldingsDTO(account.getHoldings());

		if (account.getHoldings() != null) {
			this.currentValue = account.getHoldings().getCurrentValue();
			this.openValue = account.getHoldings().getOpenValue();
			this.paperGain = account.getHoldings().getPaperGain();
			this.realizedGain = account.getHoldings().getRealizedGain();
			this.realizedIncome = account.getHoldings().getRealizedIncome();
			this.realizedCost = account.getHoldings().getRealizedCost();
			if (this.currentValue != null) {
				this.value = this.currentValue.add(this.cashBalance);
			} else {
				this.value = null;
			}
			if (this.paperGain != null) {
				this.valueChange = this.paperGain.add(this.realizedGain);
			} else {
				this.valueChange = null;
			}
		} else {
			this.currentValue = null;
			this.openValue = null;
			this.paperGain = null;
			this.realizedGain = null;
			this.realizedIncome = null;
			this.realizedCost = null;
			this.value = null;
			this.valueChange = null;
		}

		this.entries = new ArrayList<>();
		this.cashAllocationsDTO = cashAllocationsDTO;
	}

	@Override
	public String toString() {
		return name;
	}

}
