package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

public abstract class Body {
	
	protected String id;
	
	protected String gid;
	
	protected Vector2D Velocity;
	
	protected Vector2D Position;
	
	protected double Mass;
	
	protected Vector2D Force;
	
	public Body(String id, String gid, Vector2D v, Vector2D p, double m) {
		super();
		
		if(id==null || gid ==null || v == null || p == null || id.trim().length() < 1 || gid.trim().length() < 1 || m < 0 ) {
			
			throw new IllegalArgumentException("argumentos inválidos");
		}
		
		this.id = id;
		this.gid = gid;
		this.Velocity = v;
		this.Position = p;
		this.Mass = m;
		
		this.Force = new Vector2D();
	}
	

	public String getId() {
		return id;
	}

	public String getGid() {
		return gid;
	}

	public Vector2D getVelocity() {
		return Velocity;
	}

	public Vector2D getPosition() {
		return Position;
	}

	public double getMass() {
		return Mass;
	}

	public Vector2D getForce() {
		return Force;
	}
	
	void addForce(Vector2D f) {
		Force = Force.plus(f);
	}
	
	void resetForce() {
		Force = new Vector2D();
	}
	
	void advance(double t) {
		
		Vector2D a = new Vector2D();

		if (Mass != 0) {
			a = Force.scale(1/Mass);
		}
		
		Velocity = Velocity.plus(a.scale(t));
		Position = Position.plus(Velocity.scale(t).plus(a.scale(0.5*(t*t))));
		
	}
	
	public JSONObject getState() {
		JSONObject jo1 = new JSONObject();
		
		jo1.put("p", Position);
		jo1.put("id", id); //"id"
		jo1.put("m", Mass);
		jo1.put("v", Velocity);
		jo1.put("f", Force);
		//System.out.println(jo1);
		return jo1;
	}
	
	public String toString() {
		//System.out.println(getState().toString());
		return getState().toString();
	}


}
