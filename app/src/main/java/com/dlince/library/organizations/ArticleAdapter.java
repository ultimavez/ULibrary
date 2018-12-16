package com.dlince.library.organizations;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dlince.library.organizations.model.Article;

import java.util.List;


public class ArticleAdapter extends ArrayAdapter<Article> {

    private Context context;
    private List<Article> articles;

    public ArticleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
        this.context = context;
        this.articles = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_article, parent, false);

        TextView txtArId = (TextView) rowView.findViewById(R.id.txtArId);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);
        TextView txtPages = (TextView) rowView.findViewById(R.id.txtPages);
        TextView txtYear = (TextView) rowView.findViewById(R.id.txtYear);
        TextView txtTypes_id = (TextView) rowView.findViewById(R.id.txtType_id);
        TextView txtStatus = (TextView) rowView.findViewById(R.id.txtStatus);
        TextView txtEditorial_id = (TextView) rowView.findViewById(R.id.txtEditorial_id);
        TextView txtArticleCreated = (TextView) rowView.findViewById(R.id.txtArticleCreated);

        if( articles.get(pos).getStatus()==1 ){
            txtArId.setTextColor(Color.BLACK);
            txtTitle.setTextColor(Color.BLACK);
            txtPages.setTextColor(Color.BLACK);
            txtYear.setTextColor(Color.BLACK);
            txtTypes_id.setTextColor(Color.BLACK);
            txtStatus.setTextColor(Color.BLACK);
            txtEditorial_id.setTextColor(Color.BLACK);
            txtArticleCreated.setTextColor(Color.BLACK);
        }

        txtArId.setText(String.format("Code: %d", articles.get(pos).getId()));
        txtTitle.setText(String.format("Tittle: %s", articles.get(pos).getTitle()));
        txtPages.setText(String.format("Pages: %s", articles.get(pos).getPages()));
        txtYear.setText(String.format("Year: %s", articles.get(pos).getYear()));
        txtTypes_id.setText(String.format("Type id: %d", articles.get(pos).getTypes_id()));
        txtStatus.setText(String.format("Status: %s", articles.get(pos).getStatus()));
        txtEditorial_id.setText(String.format("Editorial id: %d", articles.get(pos).getEditorial_id()));

        if( articles.get(pos).getModified()==null ) {
            txtArticleCreated.setText(String.format("Created: %s", articles.get(pos).getCreated()));
        }else{
            txtArticleCreated.setText(String.format("Modified: %s", articles.get(pos).getModified()));
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, ArticleActivity.class);
                intent.putExtra("article_id", String.valueOf(articles.get(pos).getId()));
                intent.putExtra("article_title", articles.get(pos).getTitle());
                intent.putExtra("article_pages", articles.get(pos).getPages());
                intent.putExtra("article_year", articles.get(pos).getYear());
                intent.putExtra("article_type_id", String.valueOf(articles.get(pos).getTypes_id()));
                intent.putExtra("article_status", String.valueOf(articles.get(pos).getStatus()));
                intent.putExtra("article_editorial_id", String.valueOf(articles.get(pos).getEditorial_id()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
