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

public class HoldingsDTO {

	public final ArrayList<EquityHoldingDTO> equityHoldings = new ArrayList<>();
	
	public final ArrayList<EquityHoldingDTO> closedEquityHoldings = new ArrayList<>();
	
	public HoldingsDTO(HoldingList holdings) {
		for (EquityHolding holding : holdings.getEquityHoldings()) {
			equityHoldings.add(new EquityHoldingDTO(holding));
		}

		for (EquityHolding holding : holdings.getClosedEquityHoldings()) {
			closedEquityHoldings.add(new EquityHoldingDTO(holding));
		}
	}

	public class EquityHoldingDTO extends PerformanceDataDTO {
		public final String ticker;
		
		public final ArrayList<EquityPositionDTO> positions;

		public EquityHoldingDTO(EquityHolding holding) {
			super(holding);
			this.ticker = holding.getName();
			this.positions = new ArrayList<>();
			for (EquityPosition position: holding.getPositions()) {
				this.positions.add(new EquityPositionDTO(position));
			}
		}

		@Override
		public String toString() {
			return ticker;
		}
	}
	
	public class EquityPositionDTO extends PerformanceDataDTO {
		public final String name;

		public final ArrayList<EquityTradeDTO> trades;

		public EquityPositionDTO(EquityPosition position) {
			super(position);
			this.name = position.name;
			this.trades = new ArrayList<>();
			for (EquityTrade trade: position.getTrades()) {
				this.trades.add(new EquityTradeDTO(trade));
			}
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
	public class EquityTradeDTO extends PerformanceDataDTO  {
		public final String ticker;

		public EquityTradeDTO(EquityTrade trade) {
			super(trade);
			this.ticker = trade.ticker;
		}

		@Override
		public String toString() {
			return ticker;
		}
	}

	public class PerformanceDataDTO {
		public final BigDecimal quantity;

		public PerformanceDataDTO(PerformanceData objectWithPerformance) {
			this.quantity = objectWithPerformance.quantity;
		}
	}
}
