package hk.hku.cs.drawertest2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentRegulation extends Fragment {

    private TextView pdf01,pdf02;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_regulation,container,false);

        pdf01 = view.findViewById(R.id.pdf1);
        pdf02 = view.findViewById(R.id.pdf2);
        pdf01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // find current layout
                LinearLayout linearLayout = getView().findViewById(R.id.regulationLayout);
                // add webview
                WebView webView = new WebView(linearLayout.getContext());
                String pdfUrl = pdf01.getText().toString();
                String goToUrl = "http://docs.google.com/gview?embedded=true&url="+pdfUrl;
                webView.loadUrl(goToUrl);
            }
        });
        pdf02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // find current layout
                LinearLayout linearLayout = getView().findViewById(R.id.regulationLayout);
                // add webview
                WebView webView = new WebView(linearLayout.getContext());
                String pdfUrl = pdf02.getText().toString();
                String goToUrl = "http://docs.google.com/gview?embedded=true&url="+pdfUrl;
                webView.loadUrl(goToUrl);
            }
        });

        return view;
    }


}
