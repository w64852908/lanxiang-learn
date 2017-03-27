package com.lanxiang.javaadvanced.concurrent;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by lanjing on 2017/3/26.
 */
public class ExecutorsTest {

    @Test
    public void run1() {
        ExecutorService service = new ThreadPoolExecutor(10, 20, 100, TimeUnit.SECONDS, new BlockingDeque<Runnable>() {
            @Override
            public void addFirst(Runnable runnable) {

            }

            @Override
            public void addLast(Runnable runnable) {

            }

            @Override
            public boolean offerFirst(Runnable runnable) {
                return false;
            }

            @Override
            public boolean offerLast(Runnable runnable) {
                return false;
            }

            @Override
            public void putFirst(Runnable runnable) throws InterruptedException {

            }

            @Override
            public void putLast(Runnable runnable) throws InterruptedException {

            }

            @Override
            public boolean offerFirst(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public boolean offerLast(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public Runnable takeFirst() throws InterruptedException {
                return null;
            }

            @Override
            public Runnable takeLast() throws InterruptedException {
                return null;
            }

            @Override
            public Runnable pollFirst(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public Runnable pollLast(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public boolean removeFirstOccurrence(Object o) {
                return false;
            }

            @Override
            public boolean removeLastOccurrence(Object o) {
                return false;
            }

            @Override
            public boolean add(Runnable runnable) {
                return false;
            }

            @Override
            public boolean offer(Runnable runnable) {
                return false;
            }

            @Override
            public void put(Runnable runnable) throws InterruptedException {

            }

            @Override
            public boolean offer(Runnable runnable, long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public Runnable remove() {
                return null;
            }

            @Override
            public Runnable poll() {
                return null;
            }

            @Override
            public Runnable take() throws InterruptedException {
                return null;
            }

            @Override
            public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public Runnable element() {
                return null;
            }

            @Override
            public Runnable peek() {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Iterator<Runnable> iterator() {
                return null;
            }

            @Override
            public void push(Runnable runnable) {

            }

            @Override
            public Runnable removeFirst() {
                return null;
            }

            @Override
            public Runnable removeLast() {
                return null;
            }

            @Override
            public Runnable pollFirst() {
                return null;
            }

            @Override
            public Runnable pollLast() {
                return null;
            }

            @Override
            public Runnable getFirst() {
                return null;
            }

            @Override
            public Runnable getLast() {
                return null;
            }

            @Override
            public Runnable peekFirst() {
                return null;
            }

            @Override
            public Runnable peekLast() {
                return null;
            }

            @Override
            public Runnable pop() {
                return null;
            }

            @Override
            public Iterator<Runnable> descendingIterator() {
                return null;
            }

            @Override
            public int remainingCapacity() {
                return 0;
            }

            @Override
            public int drainTo(Collection<? super Runnable> c) {
                return 0;
            }

            @Override
            public int drainTo(Collection<? super Runnable> c, int maxElements) {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Runnable> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        });
    }

}
