package com.materialSnackbar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.design.widget.Snackbar;
import android.widget.FrameLayout;
import android.view.View;
import android.graphics.Color;
import android.widget.TextView;

public class MaterialSnackbar extends CordovaPlugin {

  FrameLayout layout;

  @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        layout = (FrameLayout) webView.getView().getParent();
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        try {
            if ("materialSnackbar".equals(action)) {

                JSONObject arg_object = args.getJSONObject(0);

                final String text = arg_object.getString("text");

                final String duration = arg_object.getString("duration");

                final String button = arg_object.getString("button");

				final String textColor = arg_object.getString("textColor");

				final String actionColor = arg_object.getString("actionColor");

				final String bgColor = arg_object.getString("bgColor");

                cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {

                        final Snackbar snackbar = Snackbar
                        .make(layout, text, Snackbar.LENGTH_INDEFINITE);

                        if(duration.equals("SHORT")){
                          snackbar.setDuration(Snackbar.LENGTH_SHORT);
                        } else if(duration.equals("LONG")){
                          snackbar.setDuration(Snackbar.LENGTH_LONG);
                        } else if(duration.equals("INDEFINITE")){
                          snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
                        }

                        if(!button.isEmpty()){
                          snackbar.setAction(button, new View.OnClickListener() {
                              @Override
                              public void onClick(View view) {
                                  snackbar.dismiss();
                                  callbackContext.success();
                              }
                          });
                        }

						if(!textColor.isEmpty())
							snackbar.setActionTextColor(Color.parseColor(textColor));
						
						// Getting view
						View sbView = snackbar.getView();

						if(!actionColor.isEmpty()) {
							TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
							textView.setTextColor(Color.parseColor(actionColor));
						}

						if(!bgColor.isEmpty())
							sbView.setBackgroundColor(Color.parseColor(bgColor));

                        snackbar.show();
                    }
                });
                return true;
            }
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        }
    }
}
