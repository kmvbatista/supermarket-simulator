import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

abstract class CustomConcurrentQueue<T extends Object> {
  private int capacity;
  private ArrayList<T> buffer;
  private int elementsInBuffer = 0;

  ReentrantLock mutex = new ReentrantLock();
	Condition canGet = mutex.newCondition();
	Condition canSet = mutex.newCondition();

  public CustomConcurrentQueue(int capacity) {
    this.buffer = new ArrayList<T>(capacity);
    this.capacity = capacity;
  }
	// place value into buffer
	public  void set(T value) {
		try {
			mutex.lock();
			while(elementsInBuffer >= capacity) {
				canGet.signalAll();
				canSet.await();
			}
			int positionToAdd = elementsInBuffer;
			buffer.set(positionToAdd, value);
			elementsInBuffer++;
			canGet.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			mutex.unlock();
		}
	} // end method set 

	// return value from buffer
	public T get() {
		T valueToReturn = null;
		try {
			mutex.lock();
			while (elementsInBuffer == 0) {
				canSet.signalAll();
				canGet.await();
			}
			int positionToGetValue = elementsInBuffer -1;
			valueToReturn =  buffer.get(positionToGetValue);
			clearBufferInLocation(positionToGetValue);
			canSet.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			mutex.unlock();
		}
		return valueToReturn;
	} // end method get

	private void clearBufferInLocation(int location) {
		buffer.set(location, null);
		elementsInBuffer = elementsInBuffer - 1;
	}
}