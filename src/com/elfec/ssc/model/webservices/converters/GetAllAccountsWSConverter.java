package com.elfec.ssc.model.webservices.converters;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.elfec.ssc.helpers.JsonToAccountConverter;
import com.elfec.ssc.model.Account;
import com.elfec.ssc.model.webservices.IWSResultConverter;


public class GetAllAccountsWSConverter implements IWSResultConverter<List<Account>>  {

	@Override
	public List<Account> convert(String result) {

		List<Account> accounts=new ArrayList<Account>();
		if (result != null) {
			try {
				JSONArray array = new JSONArray(result);
				for (int i = 0; i < array.length(); i++) {
					JSONObject accObj = array.getJSONObject(i);
					
					accounts.add(JsonToAccountConverter.convert(accObj));

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return accounts;
	}

}
