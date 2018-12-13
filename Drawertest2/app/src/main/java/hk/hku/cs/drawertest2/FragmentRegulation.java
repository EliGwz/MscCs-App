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

    private TextView pdf01,pdf02,pdf03,pdf04;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_regulation,container,false);

        pdf01 = view.findViewById(R.id.pdf1);
        pdf02 = view.findViewById(R.id.pdf2);
        pdf03 = view.findViewById(R.id.pdf3);
        pdf04 = view.findViewById(R.id.pdf4);
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
                pdf01.setTextColor(getResources().getColor(R.color.link_clicked));
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
                pdf02.setTextColor(getResources().getColor(R.color.link_clicked));
            }
        });
        pdf03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // find current layout
                LinearLayout linearLayout = getView().findViewById(R.id.regulationLayout);
                // add webview
                WebView webView = new WebView(linearLayout.getContext());
                String pdfUrl = pdf03.getText().toString();
                String goToUrl = "http://docs.google.com/gview?embedded=true&url="+pdfUrl;
                webView.loadUrl(goToUrl);
                pdf03.setTextColor(getResources().getColor(R.color.link_clicked));
            }
        });
        pdf04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // find current layout
                LinearLayout linearLayout = getView().findViewById(R.id.regulationLayout);
                // add webview
                WebView webView = new WebView(linearLayout.getContext());
                String pdfUrl = pdf04.getText().toString();
                String goToUrl = "http://docs.google.com/gview?embedded=true&url="+pdfUrl;
                webView.loadUrl(goToUrl);
                pdf04.setTextColor(getResources().getColor(R.color.link_clicked));
            }
        });

        return view;
    }


}
