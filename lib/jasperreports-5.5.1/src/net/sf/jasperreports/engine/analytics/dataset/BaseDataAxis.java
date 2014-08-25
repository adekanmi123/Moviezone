/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.analytics.dataset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.analytics.data.Axis;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.util.JRCloneUtils;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BaseDataAxis.java 5878 2013-01-07 20:23:13Z teodord $
 */
public class BaseDataAxis implements DataAxis, Serializable
{

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected Axis axis;
	protected List<DataAxisLevel> levels;
	
	public BaseDataAxis()
	{
		this.levels = new ArrayList<DataAxisLevel>();
	}
	
	public BaseDataAxis(DataAxis dataAxis, JRBaseObjectFactory factory)
	{
		factory.put(dataAxis, this);
		
		this.axis = dataAxis.getAxis();
		
		List<DataAxisLevel> dataLevels = dataAxis.getLevels();
		this.levels = new ArrayList<DataAxisLevel>(dataLevels.size());
		for (DataAxisLevel level : dataLevels)
		{
			this.levels.add(factory.getDataAxisLevel(level));
		}
	}

	@Override
	public Axis getAxis()
	{
		return axis;
	}

	@Override
	public List<DataAxisLevel> getLevels()
	{
		return levels;
	}

	public Object clone() 
	{
		BaseDataAxis clone = null;
		try
		{
			clone = (BaseDataAxis) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			// never
			throw new JRRuntimeException(e);
		}
		
		clone.levels = JRCloneUtils.cloneList(levels);
		return clone;
	}

}