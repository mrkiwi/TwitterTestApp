package com.test.twitter.presentation.utils;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.twitter.R;

import butterknife.ButterKnife;

public class ProgressHolder {

    protected View holder;
    protected View content;

    public ProgressHolder(@NonNull View content) {
        ViewGroup parent = (ViewGroup) content.getParent();
        this.holder = LayoutInflater.from(content.getContext()).inflate(R.layout.holder_progress, parent, false);
        this.content = content;

        holder.setVisibility(View.GONE);

        int pos = parent.indexOfChild(content);
        parent.addView(holder, pos + 1, content.getLayoutParams());

        ButterKnife.bind(this, holder);
    }

    public void setContentView(@NonNull View content) {
        ViewGroup parent = (ViewGroup) this.content.getParent();
        parent.removeView(holder);

        ViewGroup newParent = (ViewGroup) content.getParent();
        newParent.addView(holder, newParent.indexOfChild(content) + 1, content.getLayoutParams());

        this.content = content;
    }

    public void show() {
        if (holder.getVisibility() != View.VISIBLE) {
            content.setVisibility(View.GONE);
            holder.setVisibility(View.VISIBLE);
        }
    }

    public void hide() {
        if (holder.getVisibility() != View.GONE) {
            content.setVisibility(View.VISIBLE);
            holder.setVisibility(View.GONE);
        }
    }
}
