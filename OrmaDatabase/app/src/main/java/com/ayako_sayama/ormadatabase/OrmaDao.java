package com.ayako_sayama.ormadatabase;

import android.content.Context;
import android.support.annotation.Nullable;

import com.github.gfx.android.orma.AccessThreadConstraint;

import java.util.List;

/**
 * Created by ayako_sayama on 2017-03-15.
 */

public class OrmaDao {

    private static OrmaDatabase orma;

    public OrmaDao(Context context) {
        if (orma == null) {
            orma = new OrmaDatabase.Builder(context)
                    .writeOnMainThread(AccessThreadConstraint.NONE)
                    .readOnMainThread(AccessThreadConstraint.NONE)
                    .build();
        }
    }

    public void insert(Todo todo) {
        orma.relationOfTodo().inserter().execute(todo);
    }

    public void delete(Todo todo) {
        orma.relationOfTodo().deleter()
                .idEq(todo.id)
                .execute();
    }

    public void update(Todo todo) {
        orma.relationOfTodo().updater()
                .idEq(todo.id)
                .title(todo.title)
                .content(todo.content)
                .execute();
    }

    public Todo_Relation getTodoRelation() {
        return orma.relationOfTodo();
    }

    public List<Todo> selectAll() {
        return orma.relationOfTodo().selector().toList();
    }

    @Nullable
    public Todo select(long id) {
        return orma.relationOfTodo().selector()
                .idEq(id).valueOrNull();
    }

}
