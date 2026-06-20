
public abstract class Bando {
    public abstract String getNombre();
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        return true;
    }
}

