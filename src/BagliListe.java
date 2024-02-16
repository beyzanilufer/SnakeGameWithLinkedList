public class BagliListe {
    Node tail;
    Node head;

    public BagliListe() {
        this.head=null;
        this.tail=null;
    }

    public void basaEkle(Node node){//oyun sinifindan basa dugum eklemek icin kullanilan fonksiyon
        if (head==null){
            head=node;
            tail=node;
        }else {
            head.prev=node;
            node.next=head;
            head=node;
        }
    }
    public void sonaEkle(Node node){//oyun sinifindan sona dugum eklemek icin kullanilan fonksiyon
        if (head==null){
            head=node;
            tail=node;
        }else {
            tail.next=node;
            node.prev=tail;
            tail=node;
        }
    }
    public void ucuncuyuSil(){//ucuncu dugumu silen ve diger dugumlerle yeni bag olusturan fonksiyon
        Node temp=head.next.next;
        temp.prev.next=temp.next;
        temp.next.prev=temp.prev;

    }
}
