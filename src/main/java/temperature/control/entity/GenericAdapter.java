package temperature.control.entity;

import com.dalsemi.onewire.adapter.DSPortAdapter;


public class GenericAdapter {
    private volatile DSPortAdapter baseAdapter;

    public GenericAdapter() {
    }

    public GenericAdapter(DSPortAdapter baseAdapter) {
        this.baseAdapter = baseAdapter;
    }

    public DSPortAdapter getBaseAdapter() {
        return baseAdapter;
    }

    public void setBaseAdapter(DSPortAdapter baseAdapter) {
        this.baseAdapter = baseAdapter;
    }
}
