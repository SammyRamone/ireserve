package rooms;

public class ResultSRQuerry {

	private String _start;
	private String _end;
	private String _num_room;
	
	public ResultSRQuerry(String num_room, String start, String end)
	{
		_start = start;
		_num_room = num_room;
		_end = end;
	}
	
	String getStart()
	{
		return _start;
	}
	
	String getEnd()
	{
		return _end;
	}
	
	String getNumRoom()
	{
		return _num_room;
	}
	
	public String toString()
	{
		return getNumRoom() + " | " + getStart() + " | " + getEnd();
	}
	
}
