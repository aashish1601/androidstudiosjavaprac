package com.example.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;

    private String parentActivity;

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolder: Called");
        holder.textView.setText(books.get(position).getName());
        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtShortDesc.setText(books.get(position).getShortDesc());

        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, BookActivity.class);
            intent.putExtra("bookId", books.get(position).getId());
            mContext.startActivity(intent);
        });

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.cardView);
            holder.expandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.downArraw.setVisibility(View.GONE);

            switch (parentActivity) {
                case "allBooks":
                    holder.deleteText.setVisibility(View.GONE);

                    break;
                case "alreadyRead":
                    holder.deleteText.setVisibility(View.VISIBLE);

                    holder.deleteText.setOnClickListener(v -> {
                        Book book = books.get(position);
                        if (Utils.getInstance().removeFromAlreadyReadBooks(book)) {
                            books.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(mContext, "Removed from Already Read", Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
                case "currentlyReading":
                    holder.deleteText.setVisibility(View.VISIBLE);

                    holder.deleteText.setOnClickListener(v -> {
                        Book book = books.get(position);
                        if (Utils.getInstance().removeFromCurrentlyReadingBooks(book)) {
                            books.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(mContext, "Removed from Currently Reading", Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
                case "favorites":
                    holder.deleteText.setVisibility(View.VISIBLE);

                    holder.deleteText.setOnClickListener(v -> {
                        Book book = books.get(position);
                        if (Utils.getInstance().removeFromFavoriteBooks(book)) {
                            books.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(mContext, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
                case "wantToRead":
                    holder.deleteText.setVisibility(View.VISIBLE);

                    holder.deleteText.setOnClickListener(v -> {
                        Book book = books.get(position);
                        if (Utils.getInstance().removeFromWantToReadBooks(book)) {
                            books.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(mContext, "Removed from Want to Read", Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
                default:
                    holder.deleteText.setVisibility(View.GONE);
                    break;
            }

        } else {
            TransitionManager.beginDelayedTransition(holder.cardView);
            holder.expandedRelativeLayout.setVisibility(View.GONE);
            holder.downArraw.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private RelativeLayout collapsedRelLayout, expandedRelativeLayout;
        private ImageView imageView, downArraw, upArrow;
        private TextView textView, txtAuthor, txtShortDesc, deleteText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.parent);
            imageView = itemView.findViewById(R.id.imageBook);
            textView = itemView.findViewById(R.id.textBookName);

            downArraw = itemView.findViewById(R.id.imgDownArrow);
            upArrow = itemView.findViewById(R.id.imgUpArrow);
            collapsedRelLayout = itemView.findViewById(R.id.collapsedRelLayout);
            expandedRelativeLayout = itemView.findViewById(R.id.expandRelLayout);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtShortDesc = itemView.findViewById(R.id.txtShortDesc);

            deleteText = itemView.findViewById(R.id.deleteText);

            downArraw.setOnClickListener(v -> {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

            upArrow.setOnClickListener(v -> {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

            deleteText.setOnClickListener(v -> {
                Book book = books.get(getAdapterPosition());
            });
        }
    }
}
