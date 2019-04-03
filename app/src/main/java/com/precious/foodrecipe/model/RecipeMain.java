package com.precious.foodrecipe.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class RecipeMain implements Parcelable {


    protected RecipeMain(Parcel in) {
        hits = in.createTypedArrayList(Hits.CREATOR);
        count = in.readInt();
        more = in.readByte() != 0;
        to = in.readInt();
        from = in.readInt();
        q = in.readString();
    }

    public static final Creator<RecipeMain> CREATOR = new Creator<RecipeMain>() {
        @Override
        public RecipeMain createFromParcel(Parcel in) {
            return new RecipeMain(in);
        }

        @Override
        public RecipeMain[] newArray(int size) {
            return new RecipeMain[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(hits);
        parcel.writeInt(count);
        parcel.writeByte((byte) (more ? 1 : 0));
        parcel.writeInt(to);
        parcel.writeInt(from);
        parcel.writeString(q);
    }





    @Expose
    @SerializedName("hits")
    private List<Hits> hits;
    @Expose
    @SerializedName("count")
    private int count;
    @Expose
    @SerializedName("more")
    private boolean more;
    @Expose
    @SerializedName("params")
    private Params params;
    @Expose
    @SerializedName("to")
    private int to;
    @Expose
    @SerializedName("from")
    private int from;
    @Expose
    @SerializedName("q")
    private String q;

    public List<Hits> getHits() {
        return hits;
    }

    public void setHits(List<Hits> hits) {
        this.hits = hits;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean getMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }


    public static class Hits implements Parcelable {
        @Expose
        @SerializedName("bought")
        private boolean bought;
        @Expose
        @SerializedName("bookmarked")
        private boolean bookmarked;
        @Expose
        @SerializedName("recipe")
        private Recipe recipe;

        protected Hits(Parcel in) {
            bought = in.readByte() != 0;
            bookmarked = in.readByte() != 0;
            recipe = in.readParcelable(Recipe.class.getClassLoader());
        }

        public static final Creator<Hits> CREATOR = new Creator<Hits>() {
            @Override
            public Hits createFromParcel(Parcel in) {
                return new Hits(in);
            }

            @Override
            public Hits[] newArray(int size) {
                return new Hits[size];
            }
        };

        public boolean getBought() {
            return bought;
        }

        public void setBought(boolean bought) {
            this.bought = bought;
        }

        public boolean getBookmarked() {
            return bookmarked;
        }

        public void setBookmarked(boolean bookmarked) {
            this.bookmarked = bookmarked;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte((byte) (bought ? 1 : 0));
            parcel.writeByte((byte) (bookmarked ? 1 : 0));
            parcel.writeParcelable(recipe, i);
        }
    }

    public static class Recipe implements Parcelable {
        @Expose
        @SerializedName("digest")
        private List<Digest> digest;
        @Expose
        @SerializedName("totalDaily")
        private TotalDaily totalDaily;
        @Expose
        @SerializedName("totalNutrients")
        private TotalNutrients totalNutrients;
        @Expose
        @SerializedName("totalTime")
        private double totalTime;
        @Expose
        @SerializedName("totalWeight")
        private double totalWeight;
        @Expose
        @SerializedName("calories")
        private double calories;
        @Expose
        @SerializedName("ingredients")
        private List<Ingredients> ingredients;
        @Expose
        @SerializedName("ingredientLines")
        private List<String> ingredientLines;
        @Expose
        @SerializedName("cautions")
        private List<String> cautions;
        @Expose
        @SerializedName("healthLabels")
        private List<String> healthLabels;
        @Expose
        @SerializedName("dietLabels")
        private List<String> dietLabels;
        @Expose
        @SerializedName("yield")
        private int yield;
        @Expose
        @SerializedName("shareAs")
        private String shareAs;
        @Expose
        @SerializedName("url")
        private String url;
        @Expose
        @SerializedName("source")
        private String source;
        @Expose
        @SerializedName("image")
        private String image;
        @Expose
        @SerializedName("label")
        private String label;
        @Expose
        @SerializedName("uri")
        private String uri;

        public List<Digest> getDigest() {
            return digest;
        }

        public void setDigest(List<Digest> digest) {
            this.digest = digest;
        }

        public TotalDaily getTotalDaily() {
            return totalDaily;
        }

        public void setTotalDaily(TotalDaily totalDaily) {
            this.totalDaily = totalDaily;
        }

        public TotalNutrients getTotalNutrients() {
            return totalNutrients;
        }

        public void setTotalNutrients(TotalNutrients totalNutrients) {
            this.totalNutrients = totalNutrients;
        }

        public double getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(double totalTime) {
            this.totalTime = totalTime;
        }

        public double getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(double totalWeight) {
            this.totalWeight = totalWeight;
        }

        public double getCalories() {
            return calories;
        }

        public void setCalories(double calories) {
            this.calories = calories;
        }

        public List<Ingredients> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredients> ingredients) {
            this.ingredients = ingredients;
        }

        public List<String> getIngredientLines() {
            return ingredientLines;
        }

        public void setIngredientLines(List<String> ingredientLines) {
            this.ingredientLines = ingredientLines;
        }

        public List<String> getCautions() {
            return cautions;
        }

        public void setCautions(List<String> cautions) {
            this.cautions = cautions;
        }

        public List<String> getHealthLabels() {
            return healthLabels;
        }

        public void setHealthLabels(List<String> healthLabels) {
            this.healthLabels = healthLabels;
        }

        public List<String> getDietLabels() {
            return dietLabels;
        }

        public void setDietLabels(List<String> dietLabels) {
            this.dietLabels = dietLabels;
        }

        public int getYield() {
            return yield;
        }

        public void setYield(int yield) {
            this.yield = yield;
        }

        public String getShareAs() {
            return shareAs;
        }

        public void setShareAs(String shareAs) {
            this.shareAs = shareAs;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {

            parcel.writeString(label);
            parcel.writeStringList(ingredientLines);
            parcel.writeStringList(healthLabels);
            parcel.writeDouble(calories);
            parcel.writeDouble(totalTime);
            parcel.writeDouble(totalWeight);
            parcel.writeString(image);
            parcel.writeString(url);



        }

        protected Recipe(Parcel parcelable){

            label = parcelable.readString();
            ingredientLines = parcelable.createStringArrayList();
            healthLabels = parcelable.createStringArrayList();
            calories = parcelable.readDouble();
            totalTime = (int) parcelable.readDouble();
            totalWeight = parcelable.readDouble();
            image = parcelable.readString();
            url = parcelable.readString();


        }

        public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
            @Override
            public Recipe createFromParcel(Parcel parcel) {
                return new Recipe(parcel);
            }

            @Override
            public Recipe[] newArray(int i) {
                return new Recipe[i];
            }
        };
    }

    public static class Digest {
        @Expose
        @SerializedName("sub")
        private List<Sub> sub;
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("daily")
        private double daily;
        @Expose
        @SerializedName("hasRDI")
        private boolean hasRDI;
        @Expose
        @SerializedName("total")
        private double total;
        @Expose
        @SerializedName("schemaOrgTag")
        private String schemaOrgTag;
        @Expose
        @SerializedName("tag")
        private String tag;
        @Expose
        @SerializedName("label")
        private String label;

        public List<Sub> getSub() {
            return sub;
        }

        public void setSub(List<Sub> sub) {
            this.sub = sub;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getDaily() {
            return daily;
        }

        public void setDaily(double daily) {
            this.daily = daily;
        }

        public boolean getHasRDI() {
            return hasRDI;
        }

        public void setHasRDI(boolean hasRDI) {
            this.hasRDI = hasRDI;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getSchemaOrgTag() {
            return schemaOrgTag;
        }

        public void setSchemaOrgTag(String schemaOrgTag) {
            this.schemaOrgTag = schemaOrgTag;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Sub {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("daily")
        private double daily;
        @Expose
        @SerializedName("hasRDI")
        private boolean hasRDI;
        @Expose
        @SerializedName("total")
        private double total;
        @Expose
        @SerializedName("schemaOrgTag")
        private String schemaOrgTag;
        @Expose
        @SerializedName("tag")
        private String tag;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getDaily() {
            return daily;
        }

        public void setDaily(double daily) {
            this.daily = daily;
        }

        public boolean getHasRDI() {
            return hasRDI;
        }

        public void setHasRDI(boolean hasRDI) {
            this.hasRDI = hasRDI;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getSchemaOrgTag() {
            return schemaOrgTag;
        }

        public void setSchemaOrgTag(String schemaOrgTag) {
            this.schemaOrgTag = schemaOrgTag;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class TotalDaily {
        @Expose
        @SerializedName("VITK1")
        private VITK1 VITK1;
        @Expose
        @SerializedName("TOCPHA")
        private TOCPHA TOCPHA;
        @Expose
        @SerializedName("VITD")
        private VITD VITD;
        @Expose
        @SerializedName("VITB12")
        private VITB12 VITB12;
        @Expose
        @SerializedName("FOLDFE")
        private FOLDFE FOLDFE;
        @Expose
        @SerializedName("VITB6A")
        private VITB6A VITB6A;
        @Expose
        @SerializedName("NIA")
        private NIA NIA;
        @Expose
        @SerializedName("RIBF")
        private RIBF RIBF;
        @Expose
        @SerializedName("THIA")
        private THIA THIA;
        @Expose
        @SerializedName("VITC")
        private VITC VITC;
        @Expose
        @SerializedName("VITA_RAE")
        private VITA_RAE VITA_RAE;
        @Expose
        @SerializedName("P")
        private P P;
        @Expose
        @SerializedName("ZN")
        private ZN ZN;
        @Expose
        @SerializedName("FE")
        private FE FE;
        @Expose
        @SerializedName("K")
        private K K;
        @Expose
        @SerializedName("MG")
        private MG MG;
        @Expose
        @SerializedName("CA")
        private CA CA;
        @Expose
        @SerializedName("NA")
        private NA NA;
        @Expose
        @SerializedName("CHOLE")
        private CHOLE CHOLE;
        @Expose
        @SerializedName("PROCNT")
        private PROCNT PROCNT;
        @Expose
        @SerializedName("FIBTG")
        private FIBTG FIBTG;
        @Expose
        @SerializedName("CHOCDF")
        private CHOCDF CHOCDF;
        @Expose
        @SerializedName("FASAT")
        private FASAT FASAT;
        @Expose
        @SerializedName("FAT")
        private FAT FAT;
        @Expose
        @SerializedName("ENERC_KCAL")
        private ENERC_KCAL ENERC_KCAL;

        public VITK1 getVITK1() {
            return VITK1;
        }

        public void setVITK1(VITK1 VITK1) {
            this.VITK1 = VITK1;
        }

        public TOCPHA getTOCPHA() {
            return TOCPHA;
        }

        public void setTOCPHA(TOCPHA TOCPHA) {
            this.TOCPHA = TOCPHA;
        }

        public VITD getVITD() {
            return VITD;
        }

        public void setVITD(VITD VITD) {
            this.VITD = VITD;
        }

        public VITB12 getVITB12() {
            return VITB12;
        }

        public void setVITB12(VITB12 VITB12) {
            this.VITB12 = VITB12;
        }

        public FOLDFE getFOLDFE() {
            return FOLDFE;
        }

        public void setFOLDFE(FOLDFE FOLDFE) {
            this.FOLDFE = FOLDFE;
        }

        public VITB6A getVITB6A() {
            return VITB6A;
        }

        public void setVITB6A(VITB6A VITB6A) {
            this.VITB6A = VITB6A;
        }

        public NIA getNIA() {
            return NIA;
        }

        public void setNIA(NIA NIA) {
            this.NIA = NIA;
        }

        public RIBF getRIBF() {
            return RIBF;
        }

        public void setRIBF(RIBF RIBF) {
            this.RIBF = RIBF;
        }

        public THIA getTHIA() {
            return THIA;
        }

        public void setTHIA(THIA THIA) {
            this.THIA = THIA;
        }

        public VITC getVITC() {
            return VITC;
        }

        public void setVITC(VITC VITC) {
            this.VITC = VITC;
        }

        public VITA_RAE getVITA_RAE() {
            return VITA_RAE;
        }

        public void setVITA_RAE(VITA_RAE VITA_RAE) {
            this.VITA_RAE = VITA_RAE;
        }

        public P getP() {
            return P;
        }

        public void setP(P P) {
            this.P = P;
        }

        public ZN getZN() {
            return ZN;
        }

        public void setZN(ZN ZN) {
            this.ZN = ZN;
        }

        public FE getFE() {
            return FE;
        }

        public void setFE(FE FE) {
            this.FE = FE;
        }

        public K getK() {
            return K;
        }

        public void setK(K K) {
            this.K = K;
        }

        public MG getMG() {
            return MG;
        }

        public void setMG(MG MG) {
            this.MG = MG;
        }

        public CA getCA() {
            return CA;
        }

        public void setCA(CA CA) {
            this.CA = CA;
        }

        public NA getNA() {
            return NA;
        }

        public void setNA(NA NA) {
            this.NA = NA;
        }

        public CHOLE getCHOLE() {
            return CHOLE;
        }

        public void setCHOLE(CHOLE CHOLE) {
            this.CHOLE = CHOLE;
        }

        public PROCNT getPROCNT() {
            return PROCNT;
        }

        public void setPROCNT(PROCNT PROCNT) {
            this.PROCNT = PROCNT;
        }

        public FIBTG getFIBTG() {
            return FIBTG;
        }

        public void setFIBTG(FIBTG FIBTG) {
            this.FIBTG = FIBTG;
        }

        public CHOCDF getCHOCDF() {
            return CHOCDF;
        }

        public void setCHOCDF(CHOCDF CHOCDF) {
            this.CHOCDF = CHOCDF;
        }

        public FASAT getFASAT() {
            return FASAT;
        }

        public void setFASAT(FASAT FASAT) {
            this.FASAT = FASAT;
        }

        public FAT getFAT() {
            return FAT;
        }

        public void setFAT(FAT FAT) {
            this.FAT = FAT;
        }

        public ENERC_KCAL getENERC_KCAL() {
            return ENERC_KCAL;
        }

        public void setENERC_KCAL(ENERC_KCAL ENERC_KCAL) {
            this.ENERC_KCAL = ENERC_KCAL;
        }
    }

    public static class VITK1 {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class TOCPHA {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class VITD {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class VITB12 {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class FOLDFE {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class VITB6A {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class NIA {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class RIBF {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class THIA {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class VITC {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class VITA_RAE {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class P {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class ZN {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class FE {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class K {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class MG {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class CA {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class NA {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class CHOLE {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class PROCNT {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class FIBTG {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class CHOCDF {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class FASAT {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class FAT {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class ENERC_KCAL {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class TotalNutrients {
        @Expose
        @SerializedName("VITK1")
        private VITK1 VITK1;
        @Expose
        @SerializedName("TOCPHA")
        private TOCPHA TOCPHA;
        @Expose
        @SerializedName("VITD")
        private VITD VITD;
        @Expose
        @SerializedName("VITB12")
        private VITB12 VITB12;
        @Expose
        @SerializedName("FOLFD")
        private FOLFD FOLFD;
        @Expose
        @SerializedName("FOLDFE")
        private FOLDFE FOLDFE;
        @Expose
        @SerializedName("VITB6A")
        private VITB6A VITB6A;
        @Expose
        @SerializedName("NIA")
        private NIA NIA;
        @Expose
        @SerializedName("RIBF")
        private RIBF RIBF;
        @Expose
        @SerializedName("THIA")
        private THIA THIA;
        @Expose
        @SerializedName("VITC")
        private VITC VITC;
        @Expose
        @SerializedName("VITA_RAE")
        private VITA_RAE VITA_RAE;
        @Expose
        @SerializedName("P")
        private P P;
        @Expose
        @SerializedName("ZN")
        private ZN ZN;
        @Expose
        @SerializedName("FE")
        private FE FE;
        @Expose
        @SerializedName("K")
        private K K;
        @Expose
        @SerializedName("MG")
        private MG MG;
        @Expose
        @SerializedName("CA")
        private CA CA;
        @Expose
        @SerializedName("NA")
        private NA NA;
        @Expose
        @SerializedName("CHOLE")
        private CHOLE CHOLE;
        @Expose
        @SerializedName("PROCNT")
        private PROCNT PROCNT;
        @Expose
        @SerializedName("SUGAR")
        private SUGAR SUGAR;
        @Expose
        @SerializedName("FIBTG")
        private FIBTG FIBTG;
        @Expose
        @SerializedName("CHOCDF")
        private CHOCDF CHOCDF;
        @Expose
        @SerializedName("FAPU")
        private FAPU FAPU;
        @Expose
        @SerializedName("FAMS")
        private FAMS FAMS;
        @Expose
        @SerializedName("FATRN")
        private FATRN FATRN;
        @Expose
        @SerializedName("FASAT")
        private FASAT FASAT;
        @Expose
        @SerializedName("FAT")
        private FAT FAT;
        @Expose
        @SerializedName("ENERC_KCAL")
        private ENERC_KCAL ENERC_KCAL;

        public VITK1 getVITK1() {
            return VITK1;
        }

        public void setVITK1(VITK1 VITK1) {
            this.VITK1 = VITK1;
        }

        public TOCPHA getTOCPHA() {
            return TOCPHA;
        }

        public void setTOCPHA(TOCPHA TOCPHA) {
            this.TOCPHA = TOCPHA;
        }

        public VITD getVITD() {
            return VITD;
        }

        public void setVITD(VITD VITD) {
            this.VITD = VITD;
        }

        public VITB12 getVITB12() {
            return VITB12;
        }

        public void setVITB12(VITB12 VITB12) {
            this.VITB12 = VITB12;
        }

        public FOLFD getFOLFD() {
            return FOLFD;
        }

        public void setFOLFD(FOLFD FOLFD) {
            this.FOLFD = FOLFD;
        }

        public FOLDFE getFOLDFE() {
            return FOLDFE;
        }

        public void setFOLDFE(FOLDFE FOLDFE) {
            this.FOLDFE = FOLDFE;
        }

        public VITB6A getVITB6A() {
            return VITB6A;
        }

        public void setVITB6A(VITB6A VITB6A) {
            this.VITB6A = VITB6A;
        }

        public NIA getNIA() {
            return NIA;
        }

        public void setNIA(NIA NIA) {
            this.NIA = NIA;
        }

        public RIBF getRIBF() {
            return RIBF;
        }

        public void setRIBF(RIBF RIBF) {
            this.RIBF = RIBF;
        }

        public THIA getTHIA() {
            return THIA;
        }

        public void setTHIA(THIA THIA) {
            this.THIA = THIA;
        }

        public VITC getVITC() {
            return VITC;
        }

        public void setVITC(VITC VITC) {
            this.VITC = VITC;
        }

        public VITA_RAE getVITA_RAE() {
            return VITA_RAE;
        }

        public void setVITA_RAE(VITA_RAE VITA_RAE) {
            this.VITA_RAE = VITA_RAE;
        }

        public P getP() {
            return P;
        }

        public void setP(P P) {
            this.P = P;
        }

        public ZN getZN() {
            return ZN;
        }

        public void setZN(ZN ZN) {
            this.ZN = ZN;
        }

        public FE getFE() {
            return FE;
        }

        public void setFE(FE FE) {
            this.FE = FE;
        }

        public K getK() {
            return K;
        }

        public void setK(K K) {
            this.K = K;
        }

        public MG getMG() {
            return MG;
        }

        public void setMG(MG MG) {
            this.MG = MG;
        }

        public CA getCA() {
            return CA;
        }

        public void setCA(CA CA) {
            this.CA = CA;
        }

        public NA getNA() {
            return NA;
        }

        public void setNA(NA NA) {
            this.NA = NA;
        }

        public CHOLE getCHOLE() {
            return CHOLE;
        }

        public void setCHOLE(CHOLE CHOLE) {
            this.CHOLE = CHOLE;
        }

        public PROCNT getPROCNT() {
            return PROCNT;
        }

        public void setPROCNT(PROCNT PROCNT) {
            this.PROCNT = PROCNT;
        }

        public SUGAR getSUGAR() {
            return SUGAR;
        }

        public void setSUGAR(SUGAR SUGAR) {
            this.SUGAR = SUGAR;
        }

        public FIBTG getFIBTG() {
            return FIBTG;
        }

        public void setFIBTG(FIBTG FIBTG) {
            this.FIBTG = FIBTG;
        }

        public CHOCDF getCHOCDF() {
            return CHOCDF;
        }

        public void setCHOCDF(CHOCDF CHOCDF) {
            this.CHOCDF = CHOCDF;
        }

        public FAPU getFAPU() {
            return FAPU;
        }

        public void setFAPU(FAPU FAPU) {
            this.FAPU = FAPU;
        }

        public FAMS getFAMS() {
            return FAMS;
        }

        public void setFAMS(FAMS FAMS) {
            this.FAMS = FAMS;
        }

        public FATRN getFATRN() {
            return FATRN;
        }

        public void setFATRN(FATRN FATRN) {
            this.FATRN = FATRN;
        }

        public FASAT getFASAT() {
            return FASAT;
        }

        public void setFASAT(FASAT FASAT) {
            this.FASAT = FASAT;
        }

        public FAT getFAT() {
            return FAT;
        }

        public void setFAT(FAT FAT) {
            this.FAT = FAT;
        }

        public ENERC_KCAL getENERC_KCAL() {
            return ENERC_KCAL;
        }

        public void setENERC_KCAL(ENERC_KCAL ENERC_KCAL) {
            this.ENERC_KCAL = ENERC_KCAL;
        }
    }


    public static class FOLFD {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }




    public static class SUGAR {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }


    public static class FAPU {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class FAMS {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class FATRN {
        @Expose
        @SerializedName("unit")
        private String unit;
        @Expose
        @SerializedName("quantity")
        private double quantity;
        @Expose
        @SerializedName("label")
        private String label;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Ingredients {
        @Expose
        @SerializedName("weight")
        private double weight;
        @Expose
        @SerializedName("text")
        private String text;

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class Params {
        @Expose
        @SerializedName("app_id")
        private List<String> app_id;
        @Expose
        @SerializedName("calories")
        private List<String> calories;
        @Expose
        @SerializedName("to")
        private List<String> to;
        @Expose
        @SerializedName("from")
        private List<String> from;
        @Expose
        @SerializedName("health")
        private List<String> health;
        @Expose
        @SerializedName("app_key")
        private List<String> app_key;
        @Expose
        @SerializedName("q")
        private List<String> q;
        @Expose
        @SerializedName("sane")
        private List<String> sane;

        public List<String> getApp_id() {
            return app_id;
        }

        public void setApp_id(List<String> app_id) {
            this.app_id = app_id;
        }

        public List<String> getCalories() {
            return calories;
        }

        public void setCalories(List<String> calories) {
            this.calories = calories;
        }

        public List<String> getTo() {
            return to;
        }

        public void setTo(List<String> to) {
            this.to = to;
        }

        public List<String> getFrom() {
            return from;
        }

        public void setFrom(List<String> from) {
            this.from = from;
        }

        public List<String> getHealth() {
            return health;
        }

        public void setHealth(List<String> health) {
            this.health = health;
        }

        public List<String> getApp_key() {
            return app_key;
        }

        public void setApp_key(List<String> app_key) {
            this.app_key = app_key;
        }

        public List<String> getQ() {
            return q;
        }

        public void setQ(List<String> q) {
            this.q = q;
        }

        public List<String> getSane() {
            return sane;
        }

        public void setSane(List<String> sane) {
            this.sane = sane;
        }
    }
}
