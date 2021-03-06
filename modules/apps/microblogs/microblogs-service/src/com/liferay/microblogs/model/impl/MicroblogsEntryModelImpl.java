/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.microblogs.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryModel;
import com.liferay.microblogs.model.MicroblogsEntrySoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the MicroblogsEntry service. Represents a row in the &quot;MicroblogsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link MicroblogsEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MicroblogsEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryImpl
 * @see MicroblogsEntry
 * @see MicroblogsEntryModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class MicroblogsEntryModelImpl extends BaseModelImpl<MicroblogsEntry>
	implements MicroblogsEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a microblogs entry model instance should use the {@link MicroblogsEntry} interface instead.
	 */
	public static final String TABLE_NAME = "MicroblogsEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "microblogsEntryId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "creatorClassNameId", Types.BIGINT },
			{ "creatorClassPK", Types.BIGINT },
			{ "content", Types.VARCHAR },
			{ "type_", Types.INTEGER },
			{ "parentMicroblogsEntryId", Types.BIGINT },
			{ "socialRelationType", Types.INTEGER }
		};
	public static final String TABLE_SQL_CREATE = "create table MicroblogsEntry (microblogsEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,creatorClassNameId LONG,creatorClassPK LONG,content STRING null,type_ INTEGER,parentMicroblogsEntryId LONG,socialRelationType INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table MicroblogsEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY microblogsEntry.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY MicroblogsEntry.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.microblogs.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.microblogs.model.MicroblogsEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.microblogs.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.microblogs.model.MicroblogsEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.microblogs.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.microblogs.model.MicroblogsEntry"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long CREATEDATE_COLUMN_BITMASK = 2L;
	public static final long CREATORCLASSNAMEID_COLUMN_BITMASK = 4L;
	public static final long CREATORCLASSPK_COLUMN_BITMASK = 8L;
	public static final long PARENTMICROBLOGSENTRYID_COLUMN_BITMASK = 16L;
	public static final long SOCIALRELATIONTYPE_COLUMN_BITMASK = 32L;
	public static final long TYPE_COLUMN_BITMASK = 64L;
	public static final long USERID_COLUMN_BITMASK = 128L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static MicroblogsEntry toModel(MicroblogsEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		MicroblogsEntry model = new MicroblogsEntryImpl();

		model.setMicroblogsEntryId(soapModel.getMicroblogsEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCreatorClassNameId(soapModel.getCreatorClassNameId());
		model.setCreatorClassPK(soapModel.getCreatorClassPK());
		model.setContent(soapModel.getContent());
		model.setType(soapModel.getType());
		model.setParentMicroblogsEntryId(soapModel.getParentMicroblogsEntryId());
		model.setSocialRelationType(soapModel.getSocialRelationType());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<MicroblogsEntry> toModels(
		MicroblogsEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<MicroblogsEntry> models = new ArrayList<MicroblogsEntry>(soapModels.length);

		for (MicroblogsEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.microblogs.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.microblogs.model.MicroblogsEntry"));

	public MicroblogsEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _microblogsEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMicroblogsEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _microblogsEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MicroblogsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return MicroblogsEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("microblogsEntryId", getMicroblogsEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("creatorClassNameId", getCreatorClassNameId());
		attributes.put("creatorClassPK", getCreatorClassPK());
		attributes.put("content", getContent());
		attributes.put("type", getType());
		attributes.put("parentMicroblogsEntryId", getParentMicroblogsEntryId());
		attributes.put("socialRelationType", getSocialRelationType());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long microblogsEntryId = (Long)attributes.get("microblogsEntryId");

		if (microblogsEntryId != null) {
			setMicroblogsEntryId(microblogsEntryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long creatorClassNameId = (Long)attributes.get("creatorClassNameId");

		if (creatorClassNameId != null) {
			setCreatorClassNameId(creatorClassNameId);
		}

		Long creatorClassPK = (Long)attributes.get("creatorClassPK");

		if (creatorClassPK != null) {
			setCreatorClassPK(creatorClassPK);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long parentMicroblogsEntryId = (Long)attributes.get(
				"parentMicroblogsEntryId");

		if (parentMicroblogsEntryId != null) {
			setParentMicroblogsEntryId(parentMicroblogsEntryId);
		}

		Integer socialRelationType = (Integer)attributes.get(
				"socialRelationType");

		if (socialRelationType != null) {
			setSocialRelationType(socialRelationType);
		}
	}

	@JSON
	@Override
	public long getMicroblogsEntryId() {
		return _microblogsEntryId;
	}

	@Override
	public void setMicroblogsEntryId(long microblogsEntryId) {
		_microblogsEntryId = microblogsEntryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		if (_originalCreateDate == null) {
			_originalCreateDate = _createDate;
		}

		_createDate = createDate;
	}

	public Date getOriginalCreateDate() {
		return _originalCreateDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCreatorClassNameId() {
		return _creatorClassNameId;
	}

	@Override
	public void setCreatorClassNameId(long creatorClassNameId) {
		_columnBitmask |= CREATORCLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalCreatorClassNameId) {
			_setOriginalCreatorClassNameId = true;

			_originalCreatorClassNameId = _creatorClassNameId;
		}

		_creatorClassNameId = creatorClassNameId;
	}

	public long getOriginalCreatorClassNameId() {
		return _originalCreatorClassNameId;
	}

	@JSON
	@Override
	public long getCreatorClassPK() {
		return _creatorClassPK;
	}

	@Override
	public void setCreatorClassPK(long creatorClassPK) {
		_columnBitmask |= CREATORCLASSPK_COLUMN_BITMASK;

		if (!_setOriginalCreatorClassPK) {
			_setOriginalCreatorClassPK = true;

			_originalCreatorClassPK = _creatorClassPK;
		}

		_creatorClassPK = creatorClassPK;
	}

	public long getOriginalCreatorClassPK() {
		return _originalCreatorClassPK;
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return StringPool.BLANK;
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		_content = content;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@JSON
	@Override
	public long getParentMicroblogsEntryId() {
		return _parentMicroblogsEntryId;
	}

	@Override
	public void setParentMicroblogsEntryId(long parentMicroblogsEntryId) {
		_columnBitmask |= PARENTMICROBLOGSENTRYID_COLUMN_BITMASK;

		if (!_setOriginalParentMicroblogsEntryId) {
			_setOriginalParentMicroblogsEntryId = true;

			_originalParentMicroblogsEntryId = _parentMicroblogsEntryId;
		}

		_parentMicroblogsEntryId = parentMicroblogsEntryId;
	}

	public long getOriginalParentMicroblogsEntryId() {
		return _originalParentMicroblogsEntryId;
	}

	@JSON
	@Override
	public int getSocialRelationType() {
		return _socialRelationType;
	}

	@Override
	public void setSocialRelationType(int socialRelationType) {
		_columnBitmask |= SOCIALRELATIONTYPE_COLUMN_BITMASK;

		if (!_setOriginalSocialRelationType) {
			_setOriginalSocialRelationType = true;

			_originalSocialRelationType = _socialRelationType;
		}

		_socialRelationType = socialRelationType;
	}

	public int getOriginalSocialRelationType() {
		return _originalSocialRelationType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			MicroblogsEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MicroblogsEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (MicroblogsEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MicroblogsEntryImpl microblogsEntryImpl = new MicroblogsEntryImpl();

		microblogsEntryImpl.setMicroblogsEntryId(getMicroblogsEntryId());
		microblogsEntryImpl.setCompanyId(getCompanyId());
		microblogsEntryImpl.setUserId(getUserId());
		microblogsEntryImpl.setUserName(getUserName());
		microblogsEntryImpl.setCreateDate(getCreateDate());
		microblogsEntryImpl.setModifiedDate(getModifiedDate());
		microblogsEntryImpl.setCreatorClassNameId(getCreatorClassNameId());
		microblogsEntryImpl.setCreatorClassPK(getCreatorClassPK());
		microblogsEntryImpl.setContent(getContent());
		microblogsEntryImpl.setType(getType());
		microblogsEntryImpl.setParentMicroblogsEntryId(getParentMicroblogsEntryId());
		microblogsEntryImpl.setSocialRelationType(getSocialRelationType());

		microblogsEntryImpl.resetOriginalValues();

		return microblogsEntryImpl;
	}

	@Override
	public int compareTo(MicroblogsEntry microblogsEntry) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				microblogsEntry.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MicroblogsEntry)) {
			return false;
		}

		MicroblogsEntry microblogsEntry = (MicroblogsEntry)obj;

		long primaryKey = microblogsEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		MicroblogsEntryModelImpl microblogsEntryModelImpl = this;

		microblogsEntryModelImpl._originalCompanyId = microblogsEntryModelImpl._companyId;

		microblogsEntryModelImpl._setOriginalCompanyId = false;

		microblogsEntryModelImpl._originalUserId = microblogsEntryModelImpl._userId;

		microblogsEntryModelImpl._setOriginalUserId = false;

		microblogsEntryModelImpl._originalCreateDate = microblogsEntryModelImpl._createDate;

		microblogsEntryModelImpl._setModifiedDate = false;

		microblogsEntryModelImpl._originalCreatorClassNameId = microblogsEntryModelImpl._creatorClassNameId;

		microblogsEntryModelImpl._setOriginalCreatorClassNameId = false;

		microblogsEntryModelImpl._originalCreatorClassPK = microblogsEntryModelImpl._creatorClassPK;

		microblogsEntryModelImpl._setOriginalCreatorClassPK = false;

		microblogsEntryModelImpl._originalType = microblogsEntryModelImpl._type;

		microblogsEntryModelImpl._setOriginalType = false;

		microblogsEntryModelImpl._originalParentMicroblogsEntryId = microblogsEntryModelImpl._parentMicroblogsEntryId;

		microblogsEntryModelImpl._setOriginalParentMicroblogsEntryId = false;

		microblogsEntryModelImpl._originalSocialRelationType = microblogsEntryModelImpl._socialRelationType;

		microblogsEntryModelImpl._setOriginalSocialRelationType = false;

		microblogsEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MicroblogsEntry> toCacheModel() {
		MicroblogsEntryCacheModel microblogsEntryCacheModel = new MicroblogsEntryCacheModel();

		microblogsEntryCacheModel.microblogsEntryId = getMicroblogsEntryId();

		microblogsEntryCacheModel.companyId = getCompanyId();

		microblogsEntryCacheModel.userId = getUserId();

		microblogsEntryCacheModel.userName = getUserName();

		String userName = microblogsEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			microblogsEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			microblogsEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			microblogsEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			microblogsEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			microblogsEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		microblogsEntryCacheModel.creatorClassNameId = getCreatorClassNameId();

		microblogsEntryCacheModel.creatorClassPK = getCreatorClassPK();

		microblogsEntryCacheModel.content = getContent();

		String content = microblogsEntryCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			microblogsEntryCacheModel.content = null;
		}

		microblogsEntryCacheModel.type = getType();

		microblogsEntryCacheModel.parentMicroblogsEntryId = getParentMicroblogsEntryId();

		microblogsEntryCacheModel.socialRelationType = getSocialRelationType();

		return microblogsEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{microblogsEntryId=");
		sb.append(getMicroblogsEntryId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", creatorClassNameId=");
		sb.append(getCreatorClassNameId());
		sb.append(", creatorClassPK=");
		sb.append(getCreatorClassPK());
		sb.append(", content=");
		sb.append(getContent());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", parentMicroblogsEntryId=");
		sb.append(getParentMicroblogsEntryId());
		sb.append(", socialRelationType=");
		sb.append(getSocialRelationType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.microblogs.model.MicroblogsEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>microblogsEntryId</column-name><column-value><![CDATA[");
		sb.append(getMicroblogsEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>creatorClassNameId</column-name><column-value><![CDATA[");
		sb.append(getCreatorClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>creatorClassPK</column-name><column-value><![CDATA[");
		sb.append(getCreatorClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentMicroblogsEntryId</column-name><column-value><![CDATA[");
		sb.append(getParentMicroblogsEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>socialRelationType</column-name><column-value><![CDATA[");
		sb.append(getSocialRelationType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = MicroblogsEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			MicroblogsEntry.class
		};
	private long _microblogsEntryId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _originalCreateDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _creatorClassNameId;
	private long _originalCreatorClassNameId;
	private boolean _setOriginalCreatorClassNameId;
	private long _creatorClassPK;
	private long _originalCreatorClassPK;
	private boolean _setOriginalCreatorClassPK;
	private String _content;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private long _parentMicroblogsEntryId;
	private long _originalParentMicroblogsEntryId;
	private boolean _setOriginalParentMicroblogsEntryId;
	private int _socialRelationType;
	private int _originalSocialRelationType;
	private boolean _setOriginalSocialRelationType;
	private long _columnBitmask;
	private MicroblogsEntry _escapedModel;
}