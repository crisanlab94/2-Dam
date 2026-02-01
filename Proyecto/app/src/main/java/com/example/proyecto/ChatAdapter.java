package com.example.proyecto;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MensajeChat> mensajes;

    public ChatAdapter(List<MensajeChat> mensajes) { this.mensajes = mensajes; }

    @Override
    public int getItemViewType(int position) { return mensajes.get(position).getTipo(); }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MensajeChat.TIPO_USUARIO) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensaje_usuario, parent, false);
            return new UsuarioViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensaje_ia, parent, false);
            return new IAViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MensajeChat m = mensajes.get(position);
        if (holder instanceof UsuarioViewHolder) {
            ((UsuarioViewHolder) holder).tv.setText(m.getText());
        } else {
            IAViewHolder iaHolder = (IAViewHolder) holder;
            iaHolder.tv.setText(m.getText());
            // Los botones de eliminar y actualizar no tienen que hacer nada [cite: 2026-01-29]
            iaHolder.btnDelete.setOnClickListener(v -> {});
            iaHolder.btnUpdate.setOnClickListener(v -> {});
        }
    }

    @Override
    public int getItemCount() { return mensajes.size(); }

    static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        UsuarioViewHolder(View v) { super(v); tv = v.findViewById(R.id.tvMensajeUsuario); }
    }

    static class IAViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        View btnDelete, btnUpdate;
        IAViewHolder(View v) {
            super(v);
            tv = v.findViewById(R.id.tvMensajeIA);
            btnDelete = v.findViewById(R.id.btnFakeDelete);
            btnUpdate = v.findViewById(R.id.btnFakeUpdate);
        }
    }
}