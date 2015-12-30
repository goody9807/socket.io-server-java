/**
 * The MIT License
 * Copyright (c) 2010 Tad Glines
 *
 * Contributors: Ovea.com, Mycila.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.codeminders.socketio.server.transport;

import com.codeminders.socketio.server.SocketIOProtocolException;
import com.codeminders.socketio.server.SocketIOSession;
import com.codeminders.socketio.server.TransportType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

//TODO: implement CORS
public abstract class XHRPollingTransport extends AbstractHttpTransport
{
    @Override
    public TransportType getType()
    {
        return TransportType.XHR_POLLING;
    }

    @Override
    public void startSend(SocketIOSession session, ServletResponse response) throws IOException
    {
        response.setContentType("text/plain; charset=UTF-8");
    }

    @Override
    public void writeData(SocketIOSession session, ServletResponse response, String data) throws IOException
    {
        response.getOutputStream().print(data);
        response.flushBuffer();
    }

    @Override
    public void finishSend(SocketIOSession session, ServletResponse response) throws IOException
    {

    }

    @Override
    public void onConnect(SocketIOSession session, ServletRequest request, ServletResponse response) throws IOException, SocketIOProtocolException
    {
        startSend(session, response);

        //TODO: check!!!
//        writeData(session, response, SocketIOFrame.encode(SocketIOFrame.FrameType.CONNECT, session.getSessionId()));

    }
}
