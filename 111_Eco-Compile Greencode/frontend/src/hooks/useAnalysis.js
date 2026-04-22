import { useState } from 'react';
import axios from 'axios';

const useAnalysis = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const analyzeFile = async (file) => {
    setLoading(true);
    setError(null);
    setData(null);

    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await axios.post('http://localhost:8080/api/analyze', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      setData(response.data);
    } catch (err) {
      setError(err.response?.data?.message || 'Error connecting to backend server. Make sure it is running.');
      console.error('Analysis error:', err);
    } finally {
      setLoading(false);
    }
  };

  return { analyzeFile, data, loading, error };
};

export default useAnalysis;
