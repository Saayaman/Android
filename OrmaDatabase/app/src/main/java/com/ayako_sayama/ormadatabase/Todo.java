package com.ayako_sayama.ormadatabase;

import android.support.annotation.Nullable;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by ayako_sayama on 2017-03-15.
 */

@Table
public class Todo {

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column(indexed = true)
    public String title;

    @Column
    @Nullable // allows NULL (default: NOT NULL)
    public String content;

}
